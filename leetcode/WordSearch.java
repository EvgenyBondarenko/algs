public class WordSearch {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        assert(new WordSearch().new Solution().exist(board, "ABCCED"));
        assert(new WordSearch().new Solution().exist(board, "SEE"));
        assert(! new WordSearch().new Solution().exist(board, "ABCB"));
    }

    public class Solution {
        public boolean exist(char[][] board, String word) {
            if (board == null || board.length == 0) return false;
            boolean[][] visited = new boolean[board.length][board[0].length];
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (boardHasWord(board, i, j, word, 0, visited))
                        return true;
                }
            }
            return false;
        }

        private boolean boardHasWord(char[][] board, int i, int j, String word, int k, boolean[][] visited) {
            if (i < 0 || i == board.length || j < 0 || j == board[0].length
                    || visited[i][j]
                    || board[i][j] != word.charAt(k))
                return false;
            if (k == word.length()-1)
                return true;
            visited[i][j] = true;
            if (boardHasWord(board, i+1, j, word, k+1, visited)
                    || boardHasWord(board, i-1, j, word, k+1, visited)
                    || boardHasWord(board, i, j+1, word, k+1, visited)
                    || boardHasWord(board, i, j-1, word, k+1, visited))
                return true;
            visited[i][j] = false;
            return false;
        }
    }
}
