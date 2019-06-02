import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/copy-list-with-random-pointer/ */
public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Node one = new Node(1, null, null);
        Node two = new Node(2, null, null);
        Node three = new Node(3, null, null);
        Node four = new Node(4, null, null);
        one.next = two; two.next = three; three.next = four;
        one.random = three; two.random = three; three.random = two; four.random = four;
        Node n = new Solution().copyRandomList(one);
        System.out.println(n);
    }

    static class Solution {
        public Node copyRandomList(Node head) {
            Map<Node, Node> oldToNew = new HashMap<>();
            Node newHead = copy(head, oldToNew);
            Node o = head;
            Node n = newHead;
            while (n != null) {
                n.random = oldToNew.get(o.random);
                n = n.next; o = o.next;
            }

            return newHead;
        }

        private Node copy(Node n, Map<Node, Node> oldToNew) {
            if (n == null) return null;
            Node newNode = new Node(n.val, copy(n.next, oldToNew), null);
            oldToNew.put(n, newNode);
            return newNode;
        }
    }

    static class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }
}
