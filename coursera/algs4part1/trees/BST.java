public class BST<K extends Comparable<K>, V> {

    private Node<K, V> root;

    public static void main(String[] args) {
        BST<String, String> tree = new BST<>();
        tree.put("2", "two");
        System.out.println(tree);
        System.out.println(tree.get("2"));
        System.out.println(tree.get("1"));
        tree.put("1", "one");
        System.out.println(tree);
        System.out.println(tree.get("1"));
        tree.put("4", "four");
        System.out.println(tree);
        System.out.println(tree.get("4"));
        tree.put("1", "wow");
        System.out.println(tree);
        System.out.println(tree.get("1"));
        tree.put("3", "three");
        System.out.println(tree);
        System.out.println(tree.get("3"));
    }

    public void put(K k, V v) {
        if (root == null) {
            root = new Node<>(k, v);
            return;
        }
        put(root, k, v);
    }

    private Node<K, V> put(Node<K, V> node, K k, V v) {
        int cmp = k.compareTo(node.key);
        if (cmp < 0) {
            if (node.left == null) {
                node.left = new Node<>(k, v);
            } else {
                put(node.left, k, v);
            }
        } else if (cmp > 0)
            if (node.right == null) {
                node.right = new Node<>(k, v);
            } else {
                put(node.right, k, v);
            }
        else
            node.value = v;
        return node;
    }

    /**
     * this method is used in Sadgevick's lectures.
     * It indeed is more concise than mine but harder to write and understand
     */
    public void putConcise(K k, V v) {
        root = put(root, k, v);
    }

    private Node<K, V> putConcise(Node<K, V> node, K k, V v) {
        if (node == null)
            return new Node<>(k, v);
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            node.left = putConcise(node.left, k, v);
        else if (cmp > 0)
            node.right = putConcise(node.right, k, v);
        else
            node.value = v;
        return node;
    }

    public V get(K k) {
        return get(root, k);
    }

    private V get(Node<K, V> node, K k) {
        if (node == null) return null;
        int cmp = k.compareTo(node.key);
        if (cmp < 0)
            return get(node.left, k);
        if (cmp > 0)
            return get(node.right, k);
        return node.value;
    }

    @Override
    public String toString() {
        return root == null ? "null" : root.toString();
    }
}
