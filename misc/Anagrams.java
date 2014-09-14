public class Anagrams {
    public static void main(String[] args) {
        String s1 = "momo";
        String s2 = "moom";
        System.out.println(areAnagrams(s1, s2));
    }

    private static boolean areAnagrams(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] occurances = new int[65535];
        for (int i = 0; i < s1.length(); i++) {
            occurances[s1.charAt(i)]++;
        }
        for (int i = 0; i < s2.length(); i++) {
            if (--occurances[s2.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
}
