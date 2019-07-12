// https://leetcode.com/problems/friend-circles/
public class FriendCircle {

    public static void main(String[] args) {
        int[][] board = new int[][]{
                {1,1,1},
                {1,1,1},
                {0,0,1}};
        System.out.println(new Solution().findCircleNum(board));
    }

    static class Solution {
        public int findCircleNum(int[][] M) {
            if (M == null || M.length == 0) return 0;
            UnionFind uf = new UnionFind(M);
            for (int r = 0; r < M.length; r++) {
                for (int c = r + 1; c < M[0].length; c++) {
                    if (M[r][c] == 1) uf.union(r, c);
                }
            }
            return uf.numComponents;
        }

        static class UnionFind {
            int[] parents;
            int numComponents;
            UnionFind(int[][] grid) {
                parents = new int[grid.length];
                for (int i = 0; i < parents.length; i++) {
                    parents[i] = i;
                }
                numComponents = parents.length;
            }

            void union(int a, int b) {
                int aRoot = find(a);
                int bRoot = find(b);
                if (aRoot != bRoot) {
                    parents[bRoot] = aRoot;
                    numComponents--;
                }
            }

            private int find(int index) {
                if (parents[index] == index) {
                    return index;
                }
                return find(parents[index]);
            }
        }
    }
}
