import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * In-level traverse the tree changing the direction on each level.
 *
 *       1         -->
 *     /  \
 *    2    3       <--
 *   / \   / \
 *  4  5  6   7    -->
 *           / \
 *          8   9  <--
 *
 *  Should print 1, 3, 2, 4, 5, 6, 7, 9, 8
 *
 */
public class ModifiedBreadthFirstSearch {

    public static List<Integer> mbfs(Node root) {
        List<Integer> res = new ArrayList<>();
        boolean right = true;
        Deque<Node> current = new LinkedList<>();
        Deque<Node> later = new LinkedList<>();
        current.add(root);
        while(!current.isEmpty()) {
            Node node;
            if (right) {
                node = current.removeFirst();
                if (node.left != null) later.addLast(node.left);
                if (node.right != null) later.addLast(node.right);
            } else {
                node = current.removeLast();
                if (node.right != null) later.addFirst(node.right);
                if (node.left != null) later.addFirst(node.left);
            }
            res.add(node.value);
            if (current.isEmpty() && !later.isEmpty()) {
                current = later;
                later = new LinkedList<>();
                right = !right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(mbfs(buildTree()));
    }

    private static class Node {
        private int value;
        private Node left;
        private Node right;

        private Node(int value) {
            this.value = value;
        }
    }

    private static Node buildTree() {
        Node eight = new Node(8);
        Node nine = new Node(9);
        Node seven = new Node(7);
        seven.left = eight;
        seven.right = nine;
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
}
