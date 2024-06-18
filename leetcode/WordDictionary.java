class WordDictionary {

    public static void main(String[] args) {
        WordDictionary wd = new WordDictionary();
        wd.addWord("bad");
        wd.addWord("mad");
        wd.addWord("dad");
        wd.search("b..");
    }

    private Node root = new Node();

    public WordDictionary() {

    }

    public void addWord(String word) {
        Node cur = root;
        for (char c : word.toCharArray()) {
            Node n = cur.children[c - 'a'];
            if (n == null) {
                n = new Node();
                n.c = c;
                cur.children[c - 'a'] = n;
            }
            cur = n;
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return search(word.toCharArray(), 0, root);
    }

    private boolean search(char[] word, int index, Node cur) {
        for (int i = index; i < word.length; i++) {
            char c = word[i];
            if (c == '.') {
                for (Node node : cur.children) {
                    if (node != null && search(word, i+1, node)) {
                        return true;
                    }
                }
                return false;
            } else {
                Node node = cur.children[c - 'a'];
                if (node == null) {
                    return false;
                }
                cur = node;
            }
        }
        return cur != null && cur.isWord;
    }

    private static class Node {
        char c;
        Node[] children = new Node[26];
        boolean isWord;
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */