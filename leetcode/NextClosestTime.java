import java.util.Arrays;
import java.util.TreeSet;

public class NextClosestTime {
    public static void main(String[] args) {
        System.out.println(new Solution().nextClosestTime("21:12"));
    }

    static class Solution {
        public String nextClosestTime(String time) {
            int one = time.charAt(0) - '0';
            int two = time.charAt(1) - '0';
            int three = time.charAt(3) - '0';
            int four = time.charAt(4) - '0';
            TreeSet<Integer> set = new TreeSet<>(Arrays.asList(one, two, three, four));
            Integer higher = set.higher(four);
            if (higher != null) {
                return format(one, two, three, higher);
            }
            int min = set.first();
            higher = set.higher(three);
            if (higher != null && higher <= 5) {
                return format(one, two, higher, min);
            }
            higher = set.higher(two);
            if (higher != null) {
                return format(one, higher, min, min);
            }
            higher = set.higher(one);
            if (higher != null && higher <= 2) {
                return format(higher, min, min, min);
            }
            return format(min, min, min, min);
        }

        private String format(int one, int two, int three, int four) {
            return String.format("%d%d:%d%d", one, two, three, four);
        }
    }
}
