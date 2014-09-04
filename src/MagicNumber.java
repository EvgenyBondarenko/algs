public class MagicNumber {
    private static final int LOW_BOUNDARY = 317;
    private static final int HIGH_BOUNDARY = 999;
    private static int iterationCount = 0;

    public static void main(String[] args) {
        for (int hi = 100; hi < 999; hi++) {
            findMagic(hi, LOW_BOUNDARY - hi, HIGH_BOUNDARY - hi);
        }
        System.out.println("Iterations: " + iterationCount);
    }

    private static void findMagic(int hi, int p, int q) {
        if (p > q) return;
        int mid = p + (q - p) / 2;
        int cmp = compare(hi, mid);
        if (cmp < 0) {
            findMagic(hi, mid + 1, q);
        } else if (cmp > 0) {
            findMagic(hi, p, mid - 1);
        } else {
            System.out.println("Magic number: " + (hi * 1000 + mid));
        }
    }

    private static int compare(int hi, int lo) {
        iterationCount++;
        return (hi + lo) * (hi + lo) - (hi * 1000 + lo);
    }
}
