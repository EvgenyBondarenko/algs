public class PathWithMaximumGold {

    public static void main(String[] args) {
        int[][] grid = {{0,6,0}, {5,8,7}, {0,9,0}};
        System.out.println(new Solution().getMaximumGold(grid));
    }
    static class Solution {
        public int getMaximumGold(int[][] grid) {
            int max = 0;
            boolean[][] visited = new boolean[grid.length][grid[0].length];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int dfs = dfs(grid, i, j, 0, visited);
                    max = Math.max(max, dfs);
                }
            }
            return max;
        }

        private int dfs(int[][] grid, int i, int j, int collected, boolean[][] visited) {
            if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0 || visited[i][j]) {
                return collected;
            }
            visited[i][j] = true;
            int up = dfs(grid, i-1, j, collected + grid[i][j], visited);
            int down = dfs(grid, i+1, j, collected + grid[i][j], visited);
            int left = dfs(grid, i, j-1, collected + grid[i][j], visited);
            int right = dfs(grid, i, j+1, collected + grid[i][j], visited);
            visited[i][j] = false;
            return Math.max(Math.max(up, down), Math.max(left, right));
        }
    }


}
