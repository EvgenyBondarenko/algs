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
public class NumberOfIslands {

    public static void main(String[] args) {
        char[][] sea = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(new NumberOfIslands().new Solution().numIslands(sea));
    }

    public class Solution {

        public int numIslands(char[][] grid) {
            if (grid == null || grid.length == 0) return 0;
            int[][] islands = new int[grid.length][grid[0].length];
            return calculate(grid, islands);
        }

        private int calculate(char[][] grid, int[][] islands) {
            int count = 0;
            for (int i = 0; i < grid.length; i++){
                for (int j = 0; j < grid[i].length; j++) {
                    if (grid[i][j] == '1' && islands[i][j] == 0) {
                        dfs(grid, i, j, islands, ++count);
                    }
                }
            }
            return count;
        }

        private void dfs(char[][] grid, int i, int j, int[][] islands, int count) {
            islands[i][j] = count;
            // up
            if (i > 0 && grid[i-1][j] == '1' && islands[i-1][j] == 0)
                dfs(grid, i-1, j, islands, count);
            // down
            if (i < grid.length - 1 && grid[i+1][j] == '1' && islands[i+1][j] == 0)
                dfs(grid, i+1, j, islands, count);
            // left
            if (j > 0 && grid[i][j-1] == '1' && islands[i][j-1] == 0)
                dfs(grid, i, j-1, islands, count);
            // right
            if (j < grid[i].length - 1 && grid[i][j+1] == '1' && islands[i][j+1] == 0)
                dfs(grid, i, j+1, islands, count);
        }
    }
}
