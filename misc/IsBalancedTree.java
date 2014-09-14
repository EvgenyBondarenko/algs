public class IsBalancedTree {
    public static boolean isBalancedTree(Node root) {
        if (root == null) throw new IllegalArgumentException();
        Stats stats = new Stats();
        gatherStats(root.left, stats, 0);
        gatherStats(root.right, stats, 0);
        return stats.max - stats.min <= 1;
    }

    private static void gatherStats(Node node, Stats stats, int level) {
        if (node == null) return;
        if (isLeaf(node)) {
            if (stats.min > level) stats.min = level;
            if (stats.max < level) stats.max = level;
        } else {
            gatherStats(node.left, stats, level + 1);
            gatherStats(node.right, stats, level + 1);
        }
    }

    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        System.out.println(isBalancedTree(getTree()));
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

    private static class Node {
        private Object value;
        private Node left;
        private Node right;

        private Node(Object value) {
            this.value = value;
        }
    }

    private static class Stats {
        private int min = Integer.MAX_VALUE;
        private int max = Integer.MIN_VALUE;
    }
}
