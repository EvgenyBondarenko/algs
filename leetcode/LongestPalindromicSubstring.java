/** https://leetcode.com/problems/longest-palindromic-substring/ */
public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("qwwwwqqqqqqq"));
    }

    static class Solution {
        public String longestPalindrome(String s) {
            if (s == null) return null;
            if (s.isEmpty()) return s;
            int bestIndex = 0;
            int bestLength = 1;

            for (int i = 0; i < s.length(); i++) {
                int len1 = longestPalindromeWithCenterAt(s, i, i);
                int len2 = longestPalindromeWithCenterAt(s, i, i + 1);
                if (len1 > bestLength || len2 > bestLength) {
                    if (len1 > len2) {
                        bestLength = len1;
                        bestIndex = i - len1/2;
                    } else {
                        bestLength = len2;
                        bestIndex = i + 1 - len2/2;
                    }
                }
            }
            return s.substring(bestIndex, bestIndex + bestLength);
        }

        private int longestPalindromeWithCenterAt(String s, int l, int r) {
            int count = l == r ? -1 : 0;
            while (l >=0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                count += 2;
                l--; r++;
            }
            return count;
        }
    }
}
