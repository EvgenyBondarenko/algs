import java.util.*;

public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNumber().new Solution1().letterCombinations("234"));
    }

    public class Solution {

        private String[] letterBuckets = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        public List<String> letterCombinations(String digits) {
            if (digits.length() == 0) return Collections.emptyList();
            Queue<String> queue = new LinkedList<>();
            for (int i = 0; i < digits.length(); i++) {
                queue.add(letterBuckets[Integer.parseInt(String.valueOf(digits.charAt(i)))]);
            }
            return solve(queue);
        }

        private List<String> solve(Queue<String> queue) {
            if (queue.isEmpty()) return Arrays.asList("");
            String s = queue.remove();
            List<String> res = new LinkedList<>();
            List<String> leftovers = solve(queue);
            for (int i = 0; i < s.length(); i++) {
                for (String each : leftovers) {
                    res.add(s.charAt(i) + each);
                }
            }
            return res;
        }
    }

    class Solution1 {

        private char[][] mapping = populateMapping();

        private char[][] populateMapping() {
            return new char[][] {
                    {},
                    {},
                    {'a', 'b', 'c'},
                    {'d', 'e', 'f'},
                    {'g', 'h', 'i'},
                    {'j', 'k', 'l'},
                    {'m', 'n', 'o'},
                    {'p', 'q', 'r', 's'},
                    {'t', 'u', 'v'},
                    {'w', 'x', 'y', 'z'}};
        }

        public List<String> letterCombinations(String digits) {
            if (digits == null || digits.isEmpty()) return Collections.emptyList();
            List<String> result = Collections.singletonList("");
            for (char digit : digits.toCharArray()) {
                result = addChar(result, digit);
            }
            return result;
        }

        private List<String> addChar(List<String> soFar, char digit) {
            char[] chars = mapping[digit - '0'];
            List<String> withCurrChar = new ArrayList<>(soFar.size() * chars.length);
            for (String s : soFar) {
                for (char c : chars) {
                    withCurrChar.add(s + c);
                }
            }
            return withCurrChar;
        }
    }
}
