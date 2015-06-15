import java.util.Map;
import java.util.TreeMap;

public class ContainsNearbyAlmostDuplicate {

    public static void main(String[] args) {

    }

    public class Solution {
        public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
            TreeMap<Integer, Integer> tree = new TreeMap<>();
            for (int i = 0; i < nums.length; i++) {
                int current = nums[i];
                if (almostDuplicateAndNearby(tree.floorEntry(current), current, i, k, t)
                        || almostDuplicateAndNearby(tree.ceilingEntry(current), current, i, k, t)) {
                    return true;
                } else {
                    tree.put(nums[i], i);
                }
            }
            return false;
        }

        private boolean almostDuplicateAndNearby(Map.Entry<Integer, Integer> entry, int current, int i, int k, int t) {
            int top = top(current, t);
            int bottom = bottom(current, t);
            return entry != null
                    && (entry.getKey() <= top && entry.getKey() >= bottom)
                    && (entry.getValue() <= i + k && entry.getValue() >= i - k);
        }

        private int bottom(int current, int t) {
            try {
                return Math.subtractExact(current, t);
            } catch (ArithmeticException e) {
                return Integer.MIN_VALUE;
            }
        }

        private int top(int current, int t) {
            try {
                return Math.addExact(current, t);
            } catch (ArithmeticException e) {
                return Integer.MAX_VALUE;
            }
        }
    }
}
