public class Percolation {
    private final int N;
    private final boolean[][] s;
    // Represents a UF with two virtual nodes: top (indexed as 0) and bottom (indexed as N*N+1)
    private final WeightedQuickUnionUF uf;
    // Needed only to eliminate a drawback of isFull() Method
    private final WeightedQuickUnionUF ufNoBottom;

    public Percolation(int N) {
        if (N < 1) throw new IllegalArgumentException("N < 1");
        this.N = N;
        s = new boolean[N][N];
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufNoBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(2);
        p.open(1, 1);
        System.out.println(p.percolates());
        p.open(2, 2);
        System.out.println(p.percolates());
        p.open(1, 2);
        System.out.println(p.percolates());
    }

    public void open(int i, int j) {
        checkArgs(i, j);
        s[--i][--j] = true;
        connectWithOpenNeighboursOrEdges(i, j);
    }

    public boolean isOpen(int i, int j) {
        checkArgs(i, j);
        return s[--i][--j];
    }

    public boolean isFull(int i, int j) {
        checkArgs(i, j);
        return ufNoBottom.connected(convertToUFFormat(--i, --j), 0);
    }

    public boolean percolates() {
        return uf.connected(0, N * N + 1);
    }

    private void checkArgs(int i, int j) {
        if ((i < 1) || (i > N) || (j < 1) || (j > N)) throw new IndexOutOfBoundsException();
    }

    private void connectWithOpenNeighboursOrEdges(int i, int j) {
        if ((i > 0) && s[i - 1][j]) {
            uf.union(convertToUFFormat(i, j), convertToUFFormat(i - 1, j));
            ufNoBottom.union(convertToUFFormat(i, j), convertToUFFormat(i - 1, j));
        } else if (i == 0) {
            uf.union(convertToUFFormat(i, j), 0);
            ufNoBottom.union(convertToUFFormat(i, j), 0);
        }
        if ((i < N - 1) && s[i + 1][j]) {
            uf.union(convertToUFFormat(i, j), convertToUFFormat(i + 1, j));
            ufNoBottom.union(convertToUFFormat(i, j), convertToUFFormat(i + 1, j));
        } else if (i == N - 1) {
            uf.union(convertToUFFormat(i, j), N * N + 1);
        }
        if ((j > 0) && s[i][j - 1]) {
            uf.union(convertToUFFormat(i, j), convertToUFFormat(i, j - 1));
            ufNoBottom.union(convertToUFFormat(i, j), convertToUFFormat(i, j - 1));
        }
        if ((j < N - 1) && s[i][j + 1]) {
            uf.union(convertToUFFormat(i, j), convertToUFFormat(i, j + 1));
            ufNoBottom.union(convertToUFFormat(i, j), convertToUFFormat(i, j + 1));
        }
    }

    private int convertToUFFormat(int i, int j) {
        return i * N + j + 1;
    }
}
