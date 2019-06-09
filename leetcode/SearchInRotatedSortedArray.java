import java.util.Arrays;

/** https://leetcode.com/problems/search-in-rotated-sorted-array/ */
public class SearchInRotatedSortedArray {
    public static void main(String[] args) {
//        System.out.println(new Solution().search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println(new Solution().search(new int[]{3,1}, 3));
    }

    static class Solution {
        public int search(int[] nums, int target) {
            if (nums == null || nums.length == 0) return -1;
            return search(nums, 0, nums.length - 1, target);
        }

        private int search(int[] nums, int from, int to, int target) {
            if (from == to && nums[from] != target) return -1;
            if (nums[from] <= nums[to] && target >= nums[from] && target <= nums[to]) {
                int result = Arrays.binarySearch(nums, from, to + 1, target);
                return result >= 0 ? result : -1;
            }
            int mid = from + (to - from) / 2;
            int leftResult = search(nums, from, mid, target);
            return leftResult != -1 ? leftResult : search(nums, mid + 1, to, target);
        }
    }
}
