import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {

    public static void main(String[] args) {
//        [["o","a","a","n"],["e","t","a","e"],["i","h","k","r"],["i","f","l","v"]]
        char[][] board = new char[4][4];
        board[0] = new char[] {'o','a','a','n'};
        board[1] = new char[] {'e','t','a','e'};
        board[2] = new char[] {'i','h','k','r'};
        board[3] = new char[] {'i','f','l','v'};
        String[] words = new String[] {"oath","pea","eat","rain","hklf", "hf"};
        System.out.println(new WordSearch2().findWords(board, words));
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> res = new HashSet<>();
        Node root = buildTrie(words);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                dfs(board, i, j, "", visited, res, root);
            }
        }
        return res.stream().toList();
    }

    private void dfs(
        char[][] board, int i, int j, String stringSoFar,
        boolean[][] visited, Set<String> res, Node node) {
        if (i < 0 || i >= board.length
            || j < 0 || j >= board[0].length
            || visited[i][j]
            || node.children[board[i][j]-'a'] == null) {
            return;
        }
        visited[i][j] = true;
        node = node.children[board[i][j]-'a'];
        stringSoFar = stringSoFar + node.c;
        if (node.isWord) res.add(stringSoFar);
        dfs(board, i-1, j, stringSoFar, visited, res, node);
        dfs(board, i+1, j, stringSoFar, visited, res, node);
        dfs(board, i, j-1, stringSoFar, visited, res, node);
        dfs(board, i, j+1, stringSoFar, visited, res, node);
        visited[i][j] = false;
    }

    private static Node buildTrie(String[] words) {
        Node root = new Node();
        for (String word : words) {
            addWord(root, word);
        }
        return root;
    }

    private static void addWord(Node node, String word) {
        for (int i = 0; i < word.length(); i++) {
            if (node.children[word.charAt(i) - 'a'] == null) {
                Node n = new Node();
                n.c = word.charAt(i);
                node.children[word.charAt(i)-'a'] = n;
            }
            node = node.children[word.charAt(i) - 'a'];
        }
        node.isWord = true;
    }

    private static class Node {
        char c;
        boolean isWord;
        Node[] children = new Node[26];
    }
}