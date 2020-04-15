public class MaxSubArray {
    public static void main(String[] args) {
        int[] a = {-1, 3, 4, -5, 9, -2};
        System.out.println(maxSubArray(a));
    }

    public static int maxSubArray(int[] a) {
        return maxSubArray(a, 0, a.length - 1);
    }

    private static int maxSubArray(int[] a, int l, int r) {
        if (l == r) return a[l];
        int m = (l + r) / 2;
        int sumL = maxSubArray(a, l, m);
        int sumR = maxSubArray(a, m  + 1, r);
        int sumC = maxCrossing(a, l, r, m);
        return Math.max(Math.max(sumL, sumR), sumC);
    }

    private static int maxCrossing(int[] a, int l, int r, int m) {
        // find left max
        int maxLeft = a[m];
        int curLeft = a[m];
        for (int c = m - 1; c >= l; c--) {
            curLeft += a[c];
            maxLeft = Math.max(curLeft, maxLeft);
        }

        // find right max
        int maxRight = a[m + 1];
        int curRight = a[m + 1];
        for (int c = m + 2; c <= r; c++) {
            curRight += a[c];
            maxRight = Math.max(curRight, maxRight);
        }
        return maxLeft + maxRight;
    }
}
