import java.util.Arrays;

/** https://leetcode.com/problems/merge-intervals/ */
public class MergeIntervals {
    public static void main(String[] args) {
        int[][] merged = new Solution().merge(new int[][]{{1, 3}, {4, 8}, {5, 7}, {10, 20}, {11, 12}, {14, 15}, {22, 26}, {25, 30}});
        for (int[] interval : merged) {
            System.out.println("[" + interval[0] + " - " + interval[1] + "]");
        }
    }

    static class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            int[][] result = new int[intervals.length][2];
            int size = 0;
            for (int[] interval : intervals) {
                if (size == 0 || interval[0] > result[size - 1][1]) {
                    result[size++] = interval;
                } else {
                    result[size - 1][1] = Math.max(result[size - 1][1], interval[1]);
                }
            }
            return Arrays.copyOf(result, size);
        }
    }
}
