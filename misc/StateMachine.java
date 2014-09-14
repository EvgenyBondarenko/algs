import java.util.ArrayList;
import java.util.List;

public class StateMachine {

    public static void main(String[] args) {
        Parser parser = new Parser();
        assertParsed(parser.parse(""), "");
        assertParsed(parser.parse("a,b"), "a", "b");
        assertParsed(parser.parse("a,bc,cd"), "a", "bc", "cd");
        assertParsed(parser.parse("\"a\",b,c"), "a", "b", "c");
        assertParsed(parser.parse("\"a,b\",c"), "a,b", "c");
        assertParsed(parser.parse("\"a,b,c\",d"), "a,b,c", "d");
        assertParsed(parser.parse("a\"\",b"), "a\"", "b");
        assertParsed(parser.parse("\"\"\"a\",b"), "\"a", "b");
        assertParsed(parser.parse("\"\"a,b"), "\"a", "b");
        assertParsed(parser.parse("a,b\"\""), "a", "b\"");
    }

    private static void assertParsed(List<String> parse, String... strings) {
        for (int i = 0; i < strings.length; i++) {
            if (!strings[i].equals(parse.get(i))) {
                throw new AssertionError("");
            }
        }
    }

    private static enum ParserState {
        EMPTY_STRING {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                if (context.currentChar == ',') {
                    context.stringDetected();
                    return EMPTY_STRING;
                } else if (context.currentChar == 0) {
                    context.stringDetected();
                    return COMPLETE;
                } else if (context.currentChar == '\"') {
                    return ODD_NUMBER_OF_QUOTES;
                } else {
                    return CHAR_IN_A_STRING;
                }
            }
        },
        CHAR_IN_A_STRING {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                if (context.currentChar == ',') {
                    context.stringDetected();
                    return EMPTY_STRING;
                } else if (context.currentChar == 0) {
                    context.stringDetected();
                    return COMPLETE;
                } else {
                    return CHAR_IN_A_STRING;
                }
            }
        },
        ODD_NUMBER_OF_QUOTES {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                if (context.currentChar == '\"') {
                    return EMPTY_STRING;
                } else {
                    return CHAR_IN_A_QUOTED_STRING;
                }
            }
        },
        CHAR_IN_A_QUOTED_STRING {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                if (context.currentChar == '\"') {
                    return QUOTE_IN_A_QUOTED_STRING;
                } else {
                    return CHAR_IN_A_QUOTED_STRING;
                }
            }
        },
        QUOTE_IN_A_QUOTED_STRING {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                if (context.currentChar == ',') {
                    context.quotedStringDetected();
                    return EMPTY_STRING;
                } else if (context.currentChar == 0) {
                    context.quotedStringDetected();
                    return COMPLETE;
                } else {
                    return CHAR_IN_A_QUOTED_STRING;
                }
            }
        },
        COMPLETE {
            @Override
            protected ParserState doNextState(ParsingContext context) {
                throw new IllegalStateException();
            }
        };

        public ParserState nextState(ParsingContext context) {
            context.currentString.append(context.currentChar);
            return doNextState(context);
        }

        protected abstract ParserState doNextState(ParsingContext context);
    }

    private static class ParsingContext {
        char currentChar;
        StringBuilder currentString = new StringBuilder();
        List<String> parsingResult = new ArrayList<>();

        public void stringDetected() {
            removeLastComma();
            parsingResult.add(replaceEscapedQuotes());
            currentString = new StringBuilder();
        }

        public void quotedStringDetected() {
            removeLastComma();
            removeLeadingAndTrailingQuotes();
            parsingResult.add(replaceEscapedQuotes());
            currentString = new StringBuilder();
        }

        private void removeLastComma() {
            if (currentString.charAt(currentString.length() - 1) == ',') {
                currentString.deleteCharAt(currentString.length() - 1);
            }
        }

        private String replaceEscapedQuotes() {
            return currentString.toString().replace("\"\"", "\"");
        }

        private void removeLeadingAndTrailingQuotes() {
            if (currentString.length() > 0 && currentString.charAt(0) == '\"') {
                currentString.deleteCharAt(0);
                currentString.deleteCharAt(currentString.length() - 1);
            }
        }
    }

    private static class Parser {
        public List<String> parse(String csv) {
            ParserState state = ParserState.EMPTY_STRING;
            ParsingContext context = new ParsingContext();
            for (int i = 0; i < csv.length(); i++) {
                context.currentChar = csv.charAt(i);
                state = state.nextState(context);
            }
            context.currentChar = 0;
            state.nextState(context);
            return context.parsingResult;
        }
    }
}
