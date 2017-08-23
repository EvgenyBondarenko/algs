class PartitionLinkedList {

    public static void main(String[] args) {
        Node n = makeNode(new int[]{3, 6, 1, 5, 8, 2, 7}, 0);
        System.out.println(partition(n, 5));
    }

    private static Node partition(Node n, int x) {
        Node head = n;
        Node tail = n;
        n = n.next;

        while (n != null) {
            Node next = n.next;
            if (n.val < x) {
                if (tail.next != null)
                    tail.next = tail.next.next;
                Node temp = head;
                head = n;
                head.next = temp;
            } else {
                tail = n;
            }
            n = next;
        }
        return head;
    }

    private static Node makeNode(int[] a, int i) {
        if (i == a.length) {
            return null;
        }
        return new Node(a[i], makeNode(a, i + 1));

    }

    static class Node {
        private int val;
        private Node next;

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(val + " ");
            if (next != null) sb.append(next);
            return sb.toString();
        }
    }

}
