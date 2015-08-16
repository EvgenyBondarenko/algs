class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    Node<K, V> left;
    Node<K, V> right;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public static void main(String[] args) {
        Node<String, String> root = new Node<>("abc", null);
        root.left = new Node<>("cdeee", null);
        root.right = new Node<>("ff", null);
        root.left.left = new Node<>("gh", null);
        root.left.right = new Node<>("ijklmno", null);
        root.right.right = new Node<>("pq", null);
        root.right.right.left = new Node<>("llll", null);
        System.out.println(root);
    }

    public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
        if (right != null) {
            right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
        }
        sb.append(prefix).append(isTail ? "└── " : "┌── ").append(key.toString()).append("\n");
        if (left != null) {
            left.toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb);
        }
        return sb;
    }

    @Override
    public String toString() {
        return this.toString(new StringBuilder(), true, new StringBuilder()).toString();
    }

}
