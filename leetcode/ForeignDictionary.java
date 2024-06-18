import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ForeignDictionary {

    public static void main(String[] args) {
        System.out.println(new ForeignDictionary().foreignDictionary(new String[] {"wrtkj","wrt"}));
    }
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> after = new HashMap<>();
        Set<Character> unique = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                unique.add(word.charAt(i));
            }
        }

        // Build the after map
        for (int i = 0; i < words.length-1; i++) {
            String one = words[i];
            String two = words[i+1];
            int j = 0;
            while (j < one.length() && j < two.length()) {
                if (one.charAt(j) == two.charAt(j)) {
                    j++;
                    if (j == two.length() && j < one.length()) return "";
                } else {
                    char small = one.charAt(j);
                    char big = two.charAt(j);
                    if (!after.containsKey(big)) {
                        after.put(big, new HashSet<>());
                    }
                    after.get(big).add(small);
                    break;
                }
            }
        }

        char[] res = new char[unique.size()];
        int k = 0;
        int targetSize = unique.size();

        while (k < targetSize) {
            Character leading = null;
            for (Character c : unique) {
                if (!after.containsKey(c) || after.get(c).isEmpty()) {
                    leading = c;
                    unique.remove(leading);
                    res[k++] = leading;
                    for (Character key : after.keySet()) {
                        after.get(key).remove(leading);
                    }
                    break;
                }
            }
            if (leading == null) return "";
        }
        return new String(res);
    }
}
