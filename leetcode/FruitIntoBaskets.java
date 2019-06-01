/**
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * Implemented with the Sliding Window approach.
 */
public class FruitIntoBaskets {

    public static void main(String[] args) {
        int[] tree = {3,3,3,1,2,1,1,2,3,3,4}; // expected 5
//        int[] tree = {0,1,1,4,3}; // expected 3
        System.out.println(new Solution().totalFruit(tree));
    }

    static class Solution {
        public int totalFruit(int[] tree) {
            if (tree == null || tree.length == 0) return 0;
            int l = 0; int r = 0; int best = 0;
            int[] count = new int[tree.length];
            int size = 0;
            while (r < tree.length) {
                if (size == 2 && count[tree[r]] == 0) {
                    best = Math.max(best, r - l);
                    while (size == 2) {
                        count[tree[l]]--;
                        if (count[tree[l]] == 0) {
                            size--;
                        }
                        l++;
                    }
                }
                if (count[tree[r]] == 0) {
                    size++;
                }
                count[tree[r]]++;
                r++;
            }
            return Math.max(best, r - l);
        }
    }
}
