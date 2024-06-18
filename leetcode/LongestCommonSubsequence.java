import java.util.HashMap;
import java.util.Map;

public class LongestCommonSubsequence {

    private Map<String, Integer> cache = new HashMap<>();

    public static void main(String[] args) {
//        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("pqrs", "sqrp"));
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("bsbininm", "jmjkbkjkv"));
    }

    public int longestCommonSubsequence(String text1, String text2) {
        return lcs(text1, 0, text2, 0);
    }

    private int lcs(String s1, int from1, String s2, int from2) {
        int from1Original = from1;
        int from2Original = from2;
        if (from1 >= s1.length() || from2 >= s2.length()) {
            return 0;
        }
        if (cache.containsKey(from1 + "#" + from2)) {
            return cache.get(from1 + "#" + from2);
        }
        int res = 0;
        while (from1 < s1.length() && from2 < s2.length() && s1.charAt(from1) == s2.charAt(from2)) {
            res++;
            from1++;
            from2++;
        }
        int one = lcs(s1, from1+1, s2, from2);
        //cache.put(from1+1 + "#" + from2, one);
        int two = lcs(s1, from1, s2, from2+1);
        //cache.put(from1 + "#" + from2+1, two);
        res += Math.max(one, two);
        cache.put(from1Original + "#" + from2Original, res);
        return res;
    }
}
