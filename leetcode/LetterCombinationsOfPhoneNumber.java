import java.util.*;

public class LetterCombinationsOfPhoneNumber {

    public static void main(String[] args) {
        System.out.println(new LetterCombinationsOfPhoneNumber().new Solution().letterCombinations("234"));
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
}
