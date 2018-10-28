import java.util.*;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * <p>
 * Example 1:
 * <p>
 * Input: ["abcd","dcba","lls","s","sssll"]
 * Output: [[0,1],[1,0],[3,2],[2,4]]
 * Explanation: The palindromes are ["dcbaabcd","abcddcba","slls","llssssll"]
 * <p>
 * Example 2:
 * <p>
 * Input: ["bat","tab","cat"]
 * Output: [[0,1],[1,0]]
 * Explanation: The palindromes are ["battab","tabbat"]
 */
public class PalindromePairs {

    public static void main(String[] args) {
        System.out.println(new Solution().palindromePairs(
                new String[]{"a", "aa", "aaa"}
        ));
    }
}

/**
 * A + B produce palindrome if:
 * 1) A is prefix of reversed B and the rest of B is palindrome
 * 2) reversed B is a prefix of A and the rest of A is palindrome
 */
class Solution {
    private static List<Integer> findPalindromes(String word, Map<Character, Node> nodes) {
        Set<Integer> res = new HashSet<>();
        findPalindromes(nodes, word.toCharArray(), 0, res);
        return new ArrayList<>(res);
    }

    private static void findPalindromes(Map<Character, Node> nodes, char[] word, int idxInWord, Set<Integer> res) {
        Node node = nodes.get(word[idxInWord]);
        if (node == null) return;
        // 1 case: word is over
        if (idxInWord == word.length - 1) {
            if (node.idx != -1) {
                res.add(node.idx);
            }
            res.addAll(findPalindromesInChildren(node.children.entrySet()));

            // 2 case: trie is over
        } else if (node.children.isEmpty()) {
            if (isPalindrome(String.valueOf(word, idxInWord + 1, word.length - idxInWord - 1))) {
                res.add(node.idx);
            }
        } else {
            findPalindromes(node.children, word, idxInWord + 1, res);
        }
        if (node.idx != -1 && isPalindrome(String.valueOf(word, idxInWord + 1, word.length - idxInWord - 1))) {
            res.add(node.idx);
        }
    }

    private static List<Integer> findPalindromesInChildren(Set<Map.Entry<Character, Node>> entries) {
        List<Integer> res = new ArrayList<>();
        dfs(entries, "", res);
        return res;
    }

    private static void dfs(Set<Map.Entry<Character, Node>> entries, String prefix, List<Integer> res) {
        for (Map.Entry<Character, Node> entry : entries) {
            String s = prefix + entry.getKey();
            if (entry.getValue().idx != -1) {
                if (isPalindrome(s)) {
                    res.add(entry.getValue().idx);
                }
            }
            dfs(entry.getValue().children.entrySet(), s, res);
        }
    }

    private static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = chars.length - 1;
        while (i < j) {
            if (chars[i++] != chars[j--]) {
                return false;
            }
        }
        return true;
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        if (words == null || words.length == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> emptyWordIndexes = new ArrayList<>();
        List<Integer> selfPalindromeIndexes = new ArrayList<>();
        Node root = buildReversedTrie(words, emptyWordIndexes, selfPalindromeIndexes);
        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) continue;
            List<Integer> palindromeIndexes = findPalindromes(words[i], root.children);
            for (Integer palindromeIndex : palindromeIndexes) {
                addToResult(res, i, palindromeIndex);
            }
        }
        // empty string produces palindrome with every self palindrome
        for (Integer selfPalindromeIndex : selfPalindromeIndexes) {
            for (Integer emptyWordIndex : emptyWordIndexes) {
                addToResult(res, selfPalindromeIndex, emptyWordIndex);
                addToResult(res, emptyWordIndex, selfPalindromeIndex);
            }
        }
        return res;
    }

    private Node buildReversedTrie(String[] words, List<Integer> emptyWordIndexes, List<Integer> selfPalindromeIndexes) {
        Node root = new Node();
        for (int i = 0; i < words.length; i++) {
            if (words[i].isEmpty()) {
                emptyWordIndexes.add(i);
            } else {
                root.insert(words[i], i);
                if (isPalindrome(words[i])) selfPalindromeIndexes.add(i);
            }
        }
        return root;
    }

    private void addToResult(List<List<Integer>> list, int i, int j) {
        if (i == j) return;
        List<Integer> innerList = new ArrayList<>();
        innerList.add(i);
        innerList.add(j);
        list.add(innerList);
    }

    private class Node {
        Map<Character, Node> children = new HashMap<>();
        int idx = -1;

        void insert(String s, int wordIndex) {
            insert(reverse(s.toCharArray()), wordIndex, 0);
        }

        private char[] reverse(char[] source) {
            char[] res = new char[source.length];
            for (int i = 0; i < source.length; i++) {
                res[source.length - 1 - i] = source[i];
            }
            return res;
        }

        private void insert(char[] word, int wordIndex, int indexInCharArray) {
            Character c = word[indexInCharArray];
            if (!children.containsKey(c)) {
                children.put(c, new Node());
            }
            Node node = children.get(c);
            if (indexInCharArray == word.length - 1) {
                node.idx = wordIndex;
            } else {
                node.insert(word, wordIndex, indexInCharArray + 1);
            }
        }
    }
}

