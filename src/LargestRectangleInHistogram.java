public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] height = {0, 0, 0, 0, 0, 0, 0, 0, 2147483647};
        System.out.println(new LargestRectangleInHistogram().new Solution().largestRectangleArea(height));
    }

    public class Solution {
        public int largestRectangleArea(int[] height) {
            if (height == null || height.length == 0) return 0;
            int maxHeight = findMax(height);
            if (maxHeight == 0) return 0;
            int maxArea = 0;
            int[] left = new int[maxHeight];
            for (int i = 0; i < height.length; i++) {
                for (int j = 0; j < left.length; j++) {
                    left[j] = height[i] >= j ? left[j] + j : 0;
                    if (left[j] > maxArea) {
                        maxArea = left[j];
                    }
                }
            }
            return maxArea;
        }

        private int findMax(int[] height) {
            int max = height[0];
            for (int i = 1; i < height.length; i++) {
                if (height[i] > max) {
                    max = height[i];
                }
            }
            return max;
        }
    }
}
