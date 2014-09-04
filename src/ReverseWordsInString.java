import java.util.Deque;
import java.util.LinkedList;

public class ReverseWordsInString {

    public static void main(String[] args) {
        String s = "  one  two a ";
        System.out.println(new ReverseWordsInString().new Solution().reverseWords(s));

    }

    public class Solution {
        public String reverseWords(String s) {
            Deque<String> stack = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    if (sb.length() != 0) {
                        stack.push(sb.toString());
                    }
                    sb = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
            if (sb.length() != 0) {
                stack.push(sb.toString());
            }
            sb = new StringBuilder(s.length());
            for (String word : stack) {
                sb.append(word).append(' ');
            }
            if (sb.length() != 0) {
                sb.deleteCharAt(sb.length() - 1);
            }
            return sb.toString();
        }
    }
}
