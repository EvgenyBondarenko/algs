/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

 Example 1:

 11110
 11010
 11000
 00000
 Answer: 1

 Example 2:

 11000
 11000
 00100
 00011
 Answer: 3
 */
public class NumberOfIslandsUnionFind {

    public static void main(String[] args) {
        char[][] sea = {
                {'1','1','1','1','1','1','1'},
                {'0','0','0','0','0','0','1'},
                {'1','1','1','1','1','0','1'},
                {'1','0','0','0','1','0','1'},
                {'1','0','1','0','1','0','1'},
                {'1','0','1','1','1','0','1'},
                {'1','1','1','1','1','1','1'}
        };
        System.out.println(new NumberOfIslandsUnionFind().new Solution().numIslands(sea));
    }

    public class Solution {

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0 || grid[0].length == 0) return  0;
            UnionFind uf = new UnionFind(grid);
            int R = grid.length;
            int C = grid[0].length;
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (grid[r][c] == '1') {
                        if (r + 1 < R && grid[r + 1][c] == '1') {
                            uf.union(C*r + c, C*(r + 1) + c);
                        }
                        if (c + 1 < C && grid[r][c + 1] == '1') {
                            uf.union(C*r + c, C*r + c + 1);
                        }
                    }
                }
            }
            return uf.getCount();
        }

        private class UnionFind {
            int count;
            int[] parents;

            public UnionFind(char[][] grid) {
                int R = grid.length;
                int C = grid[0].length;
                parents = new int[R*C];
                for (int r = 0; r < R; r++) {
                    for (int c = 0; c < C; c++) {
                        if (grid[r][c] == '1') {
                            count++;
                            parents[C*r + c] = C*r + c;
                        }
                    }
                }
            }

            public int find(int x) {
                if (parents[x] == x) return x;
                return find(parents[x]);
            }

            public void union(int x, int y) {
                int rootX = find(x);
                int rootY = find(y);
                if (rootX != rootY) {
                    parents[rootY] = rootX;
                    count--;
                }
            }

            public int getCount() {
                return count;
            }
        }
    }
}
