/**
 * Created by jbon on 5/4/14.
 */
public class TernarySearchTree<V> {

    private Node<V> root;

    public static void main(String[] args) {
        TernarySearchTree<Integer> tree = new TernarySearchTree<Integer>();
        tree.put("KS", 1);
        tree.put("KSU", 2);
        tree.put("A", 3);
        tree.put("KA", 4);

        System.out.println(tree.get("KS"));
        System.out.println(tree.get("KSU"));
        System.out.println(tree.get("A"));
        System.out.println(tree.get("KA"));
    }

    public V get(String s) {
        return get(s, root, 0);
    }

    public void put(String s, V v) {
        root = put(s, root, v, 0);
    }

    private Node<V> put(String s, Node<V> n, V v, int d) {
        char c = s.charAt(d);
        if (n == null) {
            n = new Node<V>();
            n.c = c;
        }
        if (c < n.c) n.left = put(s, n.left, v, d);
        else if (c > n.c) n.right = put(s, n.right, v, d);
        else if (d == s.length() - 1) n.v = v;
        else n.mid = put(s, n.mid, v, d + 1);
        return n;
    }

    private V get(String s, Node n, int d) {
        if (n == null) {
            return null;
        }
        char c = s.charAt(d);
        if (c < n.c) {
            return get(s, n.left, d);
        } else if (c > n.c) {
            return get(s, n.right, d);
        } else if (d == s.length() - 1) {
            return (V) n.v;
        } else return get(s, n.mid, d + 1);
    }

    private static class Node<V> {
        char c;
        V v;
        Node<V> mid;
        Node<V> left;
        Node<V> right;
    }
}
