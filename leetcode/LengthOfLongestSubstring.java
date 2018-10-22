import java.util.HashSet;
import java.util.Set;

/**
 * Here we use the Sliding Window approach.
 * Left and right are two pointers. Right moves forward storing each character in the set. Until we see that such
 * character is already in the set. Then we remove the character that the Left points to. And keep shrinking
 * the window until the offending character is removed from the set.
 * Then we keep expanding by moving the Right forward.
 */

public class LengthOfLongestSubstring {

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestSubstring().new Solution().lengthOfLongestSubstring("pwwkew"));
    }

    class Solution {
        public int lengthOfLongestSubstring(String s) {
            if (s == null || s.isEmpty()) return 0;
            char[] source = s.toCharArray();
            Set<Character> set = new HashSet<>();
            int res = 0, left = 0, right = 0;
            while (right < source.length) {
                char cur = source[right];
                if (!set.contains(cur)) {
                    set.add(cur);
                    right++;
                    res = Math.max(res, right - left);
                } else {
                    set.remove(source[left]);
                    left++;
                }
            }
            return res;
        }
    }
}
