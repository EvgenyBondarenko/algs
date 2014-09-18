public class PercolationStats {
    private final double mean;
    private final double stddev;
    private final double confidenceLo;
    private final double confidenceHi;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) throw new IllegalArgumentException();
        double[] thresholds = new double[T];
        for (int t = 0; t < T; t++) {
            Percolation p = new Percolation(N);
            int hitCount = 0;
            while (!p.percolates()) {
                int i, j;
                do {
                    i = StdRandom.uniform(1, N + 1);
                    j = StdRandom.uniform(1, N + 1);
                } while (p.isOpen(i, j));
                p.open(i, j);
                hitCount++;
            }
            thresholds[t] = (double) hitCount / (N * N);
        }
        mean = StdStats.mean(thresholds);
        stddev = StdStats.stddev(thresholds);
        confidenceLo = mean - (1.96 * stddev / Math.sqrt(T));
        confidenceHi = mean + (1.96 * stddev / Math.sqrt(T));
    }

    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(N, T);
        System.out.println("mean \t= " + stats.mean);
        System.out.println("stddev \t= " + stats.stddev);
        System.out.println("95% confidence interval \t= " + stats.confidenceLo + ", " + stats.confidenceHi);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return confidenceLo;
    }

    public double confidenceHi() {
        return confidenceHi;
    }
}
