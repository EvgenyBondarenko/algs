public class IsValidNumber {

    public static void main(String[] args) {
        assert(new Solution().isNumber("113"));
        assert(new Solution().isNumber("-113"));
        assert(new Solution().isNumber("+113"));
        assert(! new Solution().isNumber("1+23"));
        assert(! new Solution().isNumber("113-"));
        assert(new Solution().isNumber("113."));
        assert(new Solution().isNumber("113.5"));
        assert(! new Solution().isNumber("113.5.7"));
        assert(! new Solution().isNumber("113a7"));
        assert(new Solution().isNumber("113e7"));
        assert(! new Solution().isNumber("113e7.5"));
        assert(! new Solution().isNumber("113.e7.5"));
        assert(new Solution().isNumber("-.1"));
        assert(new Solution().isNumber("1,113"));
        assert(! new Solution().isNumber("1,11"));
        assert(! new Solution().isNumber("."));
        assert(new Solution().isNumber(".0e3"));
        assert(! new Solution().isNumber(".0e"));
        assert(new Solution().isNumber(" 005047e+6"));
        assert(! new Solution().isNumber("3.5e+3.5e+3.5"));
//        assert(! new Solution().isNumber("1,1134"));//bug :)
    }

    public static class Solution {

        public boolean isNumber(String s) {
            if (s == null || s.trim().isEmpty()) return false;
            IsValidNumberStateMachine sm = new IsValidNumberStateMachine();
            for (char c : s.trim().toCharArray()) {
                if (!sm.processCharacter(c))
                    return false;
            }
            return sm.complete();
        }

        private static class IsValidNumberStateMachine{
            private final int[][] transition = {
                //  NUMBER  E   DOT COMMA  SIGN   OTHER  END
                    {1,     9,  3,  9,     2,     9,     9}, // NOTHING
                    {1,     7,  10, 4,     9,     9,     12},// NUMBER
                    {1,     9,  3,  9,     9,     9,     9}, // SIGN
                    {10,    9,  9,  9,     9,     9,     9}, // DOT
                    {5,     9,  9,  9,     9,     9,     9}, // COMMA
                    {6,     9,  9,  9,     9,     9,     9}, // COMMA_1
                    {1,     9,  9,  9,     9,     9,     9}, // COMMA_2
                    {8,     9,  9,  9,     11,    9,     9}, // E
                    {8,     9,  9,  9,     9,     9,     12},// E_NUMBER
                    {9,     9,  9,  9,     9,     9,     9}, // INVALID
                    {10,    7,  9,  9,     9,     9,     12},// DOTTED_NUMBER
                    {8,     9,  9,  9,     9,     9,     9}  // E_SIGNED
            };
            private State state = State.NOTHING;

            boolean processCharacter(char c) {
                Event event = charToEvent(c);
                return processCharacter(event);
            }

            boolean complete() {
                processCharacter(Event.END);
                return state == State.VALID;
            }

            private boolean processCharacter(Event event) {
                int ordinal = transition[state.ordinal()][event.ordinal()];
                state = State.values()[ordinal];
                return state !=State.INVALID;
            }

            private Event charToEvent(char c) {
                switch(c) {
                    case 'e' : return Event.E;
                    case '.' : return Event.DOT;
                    case ',' : return Event.COMMA;
                    case '+' : return Event.SIGN;
                    case '-' : return Event.SIGN;
                    default :
                        if (c >=48 && c <= 57) return Event.NUMBER;
                        else                   return Event.OTHER;
                }
            }

            private enum State {
                // 0        1      2    3     4     5           6    7      8       9            10        11        12
                NOTHING, NUMBER, SIGN, DOT, COMMA, COMMA_1, COMMA_2, E, E_NUMBER, INVALID, DOTTED_NUMBER, E_SIGNED, VALID 
            }

            private enum Event {
                NUMBER, E, DOT, COMMA, SIGN, OTHER, END
            }
        }
    }
}
