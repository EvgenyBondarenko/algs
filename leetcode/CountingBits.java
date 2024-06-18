public class CountingBits {

    public static void main(String[] args) {
        new CountingBits().countBits(10);
    }

    public int[] countBits(int n) {
        // 0 -> 0 -> 0
        // 1 -> 1 -> 1
        // 2 -> 10 -> 1
        // 3 -> 11 -> 2
        // 4 -> 100 -> 1
        // 5 -> 101 -> 2
        // 6 -> 110 -> 2
        // 7 -> 111 -> 3
        int[] res = new int[n+1];
        for (int i = 1; i <=n; i++) {
            int rem = i % 2;
            int m = i / 2;
            res[i] = rem + res[m];
        }
        return res;
    }

}
