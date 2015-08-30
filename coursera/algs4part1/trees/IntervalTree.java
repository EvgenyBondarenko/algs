public class IntervalTree {

    private IntervalNode root;

    public static void main(String[] args) {
        IntervalTree it = new IntervalTree();
        it.add(10, 12);
        it.add(9, 10);
        it.add(4, 7);
        it.add(5, 16);
        it.add(12, 15);
        it.add(19, 20);
        System.out.println(it);
        System.out.println("Intersects with 5-6:" + it.intersects(5, 6));
        System.out.println("Intersects with 1-2:" + it.intersects(1, 2));
        System.out.println("Intersects with 3-4:" + it.intersects(3, 4));
        System.out.println("Intersects with 3-5:" + it.intersects(3, 5));
        System.out.println("Intersects with 11-12:" + it.intersects(11, 12));
        System.out.println("Intersects with 16-17:" + it.intersects(16, 17));
        System.out.println("Intersects with 16-20:" + it.intersects(16, 20));
    }

    public void add(int start, int end) {
        root = add(root, start, end);
    }

    private IntervalNode add(IntervalNode node, int start, int end) {
        if (node == null)
            return new IntervalNode(start, end, end);
        if (end > node.maxInSubTree)
            node.maxInSubTree = end;
        if (start > node.start)
            node.right = add(node.right, start, end);
        else
            node.left = add(node.left, start, end);
        return node;
    }

    public boolean intersects(int start, int end) {
        return intersects(root, start, end);
    }

    private boolean intersects(IntervalNode node, int start, int end) {
        if (node == null || start > node.maxInSubTree)
            return false;
        if (intersectsWithNode(node, start, end))
            return true;
        if (start < node.start)
            return intersects(node.left, start, end);
        else return intersects(node.left, start, end) || intersects(node.right, start, end);
    }

    private boolean intersectsWithNode(IntervalNode node, int start, int end) {
        return (start >= node.start && start < node.end)
                || (end > node.start && end <= node.end);
    }

    @Override
    public String toString() {
        return root.toString();
    }

    private class IntervalNode {
        private final int start;
        private final int end;
        private int maxInSubTree;
        private IntervalNode left;
        private IntervalNode right;

        public IntervalNode(int start, int end, int maxInSubTree) {
            this.start = start;
            this.end = end;
            this.maxInSubTree = maxInSubTree;
        }

        public StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb) {
            if (right != null) {
                right.toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb);
            }
            sb.append(prefix).append(isTail ? "└── " : "┌── ").append(start).append("-").append(end).append("(").append(maxInSubTree).append(")").append("\n");
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
}
