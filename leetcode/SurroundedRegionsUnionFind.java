//Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
//
//        A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//        For example,
//        X X X X
//        X O O X
//        X X O X
//        X O X X
//        After running your function, the board should be:
//
//        X X X X
//        X X X X
//        X X X X
//        X O X X
public class SurroundedRegionsUnionFind {

    public static void main(String[] args) {
        int n = 4;
        int m = 4;
        char[][] board = allXBoard(n, m);
        board[1][1] = 'O';
        board[1][2] = 'O';
        board[2][2] = 'O';
        board[3][1] = 'O';
        print(board);
        System.out.println();
        new SurroundedRegionsUnionFind().new Solution().solve(board);
        print(board);

    }

    private static void print(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    private static char[][] allXBoard(int n, int m) {
        char[][] board = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = 'X';
            }
        }
        return board;
    }

    public class Solution {
        public void solve(char[][] board) {
            int n = board.length;
            int m = board[0].length;
            UnionFind uf = new UnionFind(n * m + 1);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (board[i][j] == 'O') {
                        if ((i == 0 || j == 0)) {
                            uf.union(convert(i, j, n), n * m);
                        } else if (board[i][j] == board[i - 1][j]) {
                            uf.union(convert(i, j, n), convert(i - 1, j, n));
                        } else if (board[i][j] == board[i][j - 1]) {
                            uf.union(convert(i, j, n), convert(i, j - 1, n));
                        }
                    }
                }
            }
            for (int i = 1; i < n - 1; i++) {
                for (int j = 1; j < m - 1; j++) {
                    if (board[i][j] == 'O' && !uf.connected(convert(i, j, n), n * m)) {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private int convert(int x, int y, int width) {
            return x * width + y;
        }
    }

    class UnionFind {
        private int[] u;
        private int[] rank;

        public UnionFind(int size) {
            u = new int[size];
            for (int i = 0; i < size; i++) {
                u[i] = i;
            }
            rank = new int[size];
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
}
