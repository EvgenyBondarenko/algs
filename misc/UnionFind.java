public class UnionFind {
    private int[] u;
    private int[] rank;

    public UnionFind(int size) {
        u = new int[size];
        for (int i = 0; i < size; i++) {
            u[i] = i;
        }
        rank = new int[size];
    }

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(8);
        uf.union(1, 2);
        uf.union(1, 3);
        uf.union(6, 7);
        uf.union(1, 6);
        System.out.println("7 and 3 connected? " + uf.connected(7, 3));
        System.out.println("4 and 3 connected? " + uf.connected(4, 3));
    }

    public void union(int a, int b) {
        int aParent = getParent(a);
        int bParent = getParent(b);
        if (rank[aParent] > rank[bParent]) {
            makeFirstSubnodeOfSecond(bParent, aParent);
        } else if (rank[bParent] > rank[aParent]) {
            makeFirstSubnodeOfSecond(aParent, bParent);
        } else {
            makeFirstSubnodeOfSecond(aParent, bParent);
            rank[bParent]++;
        }
    }

    public boolean connected(int a, int b) {
        return getParent(a) == getParent(b);
    }

    private void makeFirstSubnodeOfSecond(int first, int second) {
        u[first] = second;
    }

    private int getParent(int x) {
        int prev;
        do {
            prev = x;
            x = u[x];
        } while (x != prev);
        return x;
    }
}
