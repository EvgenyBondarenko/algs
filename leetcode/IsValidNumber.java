public class IsValidNumber {

    public static void main(String[] args) {
        System.out.println(new Solution().isNumber("123"));
        System.out.println(new Solution().isNumber("-123"));
        System.out.println(new Solution().isNumber("+123"));
        System.out.println(new Solution().isNumber("1+23"));
        System.out.println(new Solution().isNumber("123-"));
        System.out.println(new Solution().isNumber("123."));
        System.out.println(new Solution().isNumber("123.5"));
        System.out.println(new Solution().isNumber("123.5.7"));
        System.out.println(new Solution().isNumber("123a7"));
        System.out.println(new Solution().isNumber("123e7"));
        System.out.println(new Solution().isNumber("123e7.5"));
        System.out.println(new Solution().isNumber("-.1"));
        System.out.println(new Solution().isNumber("1,123"));
        System.out.println(new Solution().isNumber("1,12"));
        System.out.println(new Solution().isNumber("1,1234"));//bug :)
    }

    public static class Solution {

        public boolean isNumber(String s) {
            if (s == null || s.trim().isEmpty()) return false;
            IsValidNumberStateMachine sm = new IsValidNumberStateMachine();
            for (char c : s.trim().toCharArray()) {
                sm.processCharacter(c);
            }
            return sm.complete();
        }

        private static class IsValidNumberStateMachine {
            private final int[][] transition = {
                    //  NUMBER  E   DOT COMMA   SIGN    OTHER   END
                    {1, 11, 3, 11, 2, 11, 11}, // NOTHING
                    {1, 7, 3, 4, 11, 11, 12}, // NUMBER
                    {1, 11, 3, 11, 11, 11, 11}, // SIGN
                    {10, 11, 11, 11, 11, 11, 11}, // DOT
                    {5, 11, 11, 11, 11, 11, 11}, // COMMA
                    {6, 11, 11, 11, 11, 11, 11}, // COMMA_1
                    {1, 11, 11, 11, 11, 11, 11}, // COMMA_2
                    {8, 11, 11, 11, 11, 11, 11}, // E
                    {8, 11, 9, 11, 11, 11, 12}, // E_NUMBER
                    {1, 7, 11, 4, 11, 11, 12}, // E_NUMBER_DOT
                    {8, 11, 11, 11, 11, 11, 12}, // DOTTED_NUMBER
                    {11, 11, 11, 11, 11, 11, 11}  // INVALID
            };
            private State state = State.NOTHING;

            void processCharacter(char c) {
                Event event = charToEvent(c);
                processCharacter(event);
            }

            boolean complete() {
                processCharacter(Event.END);
                return state == State.VALID;
            }

            private void processCharacter(Event event) {
                int ordinal = transition[state.ordinal()][event.ordinal()];
                state = State.values()[ordinal];
            }

            private Event charToEvent(char c) {
                switch (c) {
                    case 'e':
                        return Event.E;
                    case '.':
                        return Event.DOT;
                    case ',':
                        return Event.COMMA;
                    case '+':
                        return Event.SIGN;
                    case '-':
                        return Event.SIGN;
                    default:
                        if (c >= 48 && c <= 57) return Event.NUMBER;
                        else return Event.OTHER;
                }
            }

            private enum State {
                // 0        1      2    3     4     5           6    7      8       9             10            11      12
                NOTHING, NUMBER, SIGN, DOT, COMMA, COMMA_1, COMMA_2, E, E_NUMBER, E_NUMBER_DOT, DOTTED_NUMBER, INVALID, VALID
            }

            private enum Event {
                NUMBER, E, DOT, COMMA, SIGN, OTHER, END
            }
        }
    }
}
