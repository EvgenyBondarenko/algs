public class TreeTraversal {
    public static void main(String[] args) {
        TreeTraversal treeTraversal = new TreeTraversal();
        Node root = getTree();
        System.out.println("PreOrder");
        treeTraversal.preOrder(root);
        System.out.println("\nInOrder");
        treeTraversal.inOrder(root);
        System.out.println("\nPostOrder");
        treeTraversal.postOrder(root);
    }

    private static Node getTree() {
        Node eight = new Node(8);
        Node seven = new Node(7);
        seven.left = eight;
        Node six = new Node(6);
        Node five = new Node(5);
        Node four = new Node(4);
        Node three = new Node(3);
        three.left = six;
        three.right = seven;
        Node two = new Node(2);
        two.left = four;
        two.right = five;
        Node one = new Node(1);
        one.left = two;
        one.right = three;
        return one;
    }

    public void preOrder(Node node) {
        if (node == null) return;
        process(node);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void inOrder(Node node) {
        if (node == null) return;
        inOrder(node.left);
        process(node);
        inOrder(node.right);
    }

    public void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        process(node);
    }

    private void process(Node node) {
        if (node != null) System.out.print(node.value);
    }

    private static class Node {
        private Object value;
        private Node left;
        private Node right;

        private Node(Object value) {
            this.value = value;
        }
    }
}
