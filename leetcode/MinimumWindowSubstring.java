import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {

    public static void main(String[] args) {
        new MinimumWindowSubstring().minWindow("cabwefgewcwaefgcf", "cae");
    }
    public String minWindow(String s, String t) {
        String res = "";
        int L = 0, R = 0;
        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : t.toCharArray()) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }
        Map<Character, Integer> sMap = new HashMap<>();
        int need = tMap.size();
        int have = 0;

        while (R < s.length()) {
            char curChar = s.charAt(R);
            int newCount = sMap.getOrDefault(curChar, 0) + 1;
            sMap.put(curChar, newCount);

            if (tMap.containsKey(curChar) && tMap.get(curChar) == newCount) {
                have++;
            }
            while (have == need) {
                // we found a possible answer
                if (res.isEmpty() || res.length() > R - L + 1) {
                    res = s.substring(L, R + 1);
                }
                // try to shorten the answer by dropping unneeded leading characters
                curChar = s.charAt(L);
                L++;
                sMap.put(curChar, sMap.get(curChar) - 1);
                if (tMap.containsKey(curChar) && sMap.get(curChar) < tMap.get(curChar)) {
                    have--;
                } else if (res.isEmpty() || res.length() > R - L + 1) {
                    res = s.substring(L, R + 1);
                }
            }
            R++;
        }
        return res;
    }
}
