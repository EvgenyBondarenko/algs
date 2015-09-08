import java.util.HashMap;
import java.util.Map;

class TrieNode {

    Character c;
    Map<Character, TrieNode> children;
    boolean isLastChar;

    public TrieNode(Character c, boolean isLastChar) {
        this.c = c;
        children = new HashMap<>();
        this.isLastChar = isLastChar;
    }
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode((char) 0, false);
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        insert(word, 0, root);
    }

    private void insert(String word, int index, TrieNode node) {
        boolean isLastChar = index == word.length() - 1;
        char currentChar = word.charAt(index);
        TrieNode nodeForCurrentChar = node.children.get(currentChar);
        if (nodeForCurrentChar == null) {
            nodeForCurrentChar = new TrieNode(currentChar, isLastChar);
            node.children.put(currentChar, nodeForCurrentChar);
        }
        else if (isLastChar)
            nodeForCurrentChar.isLastChar = true;
        if (!isLastChar)
            insert(word, index + 1, nodeForCurrentChar);
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int index, TrieNode node) {
        char currentChar = word.charAt(index);
        TrieNode nodeForCurrentChar = node.children.get(currentChar);
        if (nodeForCurrentChar == null)
            return false;
        boolean isLastChar = index == word.length() - 1;
        if (isLastChar)
            return nodeForCurrentChar.isLastChar;
        return search(word, index + 1, nodeForCurrentChar);
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0, root);
    }

    private boolean startsWith(String prefix, int index, TrieNode node) {
        char currentChar = prefix.charAt(index);
        TrieNode nodeForCurrentChar = node.children.get(currentChar);
        if (nodeForCurrentChar == null)
            return false;
        boolean isLastChar = index == prefix.length() - 1;
        return isLastChar || startsWith(prefix, index + 1, nodeForCurrentChar);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("somestring");
        assert !trie.search("key");
        assert trie.startsWith("some");
        assert !trie.search("some");
        assert trie.search("somestring");
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");