import java.util.ArrayList;
import java.util.List;

public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {
        int[][] heights = new int[][] {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        new PacificAtlanticWaterFlow().pacificAtlantic(heights);
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        boolean[][] p = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            dfs(heights, i, 0, p);
        }
        for (int j = 0; j < heights[0].length; j++) {
            dfs(heights, 0, j, p);
        }
        boolean[][] a = new boolean[heights.length][heights[0].length];
        for (int i = 0; i < heights.length; i++) {
            dfs(heights, i, heights[0].length-1, a);
        }
        for (int j = 0; j < heights[0].length; j++) {
            dfs(heights, heights.length-1, j, a);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (p[i][j] && a[i][j]) {
                    res.add(List.of(i, j));
                }
            }
        }
        return res;
    }

    private static void dfs(int[][] grid, int i, int j, boolean[][] m) {
        if (m[i][j]) return;
        m[i][j] = true;
        // up
        if (i>=1 && grid[i][j] <= grid[i-1][j]) dfs(grid, i-1, j, m);
        // down
        if (i< grid.length-1 && grid[i][j] <= grid[i+1][j]) dfs(grid, i+1, j, m);
        // left
        if (j>=1 && grid[i][j] <= grid[i][j-1]) dfs(grid, i, j-1, m);
        // right
        if (j< grid[0].length-1 && grid[i][j] <= grid[i][j+1]) dfs(grid, i, j+1, m);
    }

}
