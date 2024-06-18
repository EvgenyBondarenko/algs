import java.util.Arrays;

public class NonOverlappingIntervals {

    public static void main(String[] args) {
        int[][] a = new int[3][2];
        a[0] = new int[] {1,2};
        a[1] = new int[] {1,2};
        a[2] = new int[] {1,2};
        new NonOverlappingIntervals().eraseOverlapIntervals(a);
    }
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        return erase(intervals, 0, 1, 0);
    }

    private int erase(int[][] intervals, int p, int q, int removed) {
        for (int i = q; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[p][1]) {
                // overlapping case
                int removedCurrent = erase(intervals, p, i+1, removed+1);
                int removedPrev = erase(intervals, p+1, i+1, removed+1);
                removed = Math.min(removedCurrent, removedPrev);
            } else {
                p++;
            }
        }
        return removed;
    }
}