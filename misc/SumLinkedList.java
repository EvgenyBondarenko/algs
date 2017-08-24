class SumLinkedList {

    public static void main(String[] args) {
        Node a = makeNode(new int[]{7,1,6,1}, 0);
        Node b = makeNode(new int[]{5,9,2}, 0);
        System.out.println(sum(a, b));
    }

    private static Node sum(Node a, Node b) {
        Node s = new Node(0, null);
        Node sHolder = s;
        boolean carry = false;

        while (a != null || b != null) {
            int aVal = a == null ? 0 : a.val;
            int bVal = b == null ? 0 : b.val;
            int sVal = aVal + bVal + (carry ? 1 : 0);
            if (sVal > 9) {
                carry = true;
                sVal -= 10;
            } else
                carry = false;
            s.next = new Node(sVal, null);
            if (a != null) a = a.next;
            if (b != null) b = b.next;
            s = s.next;
        }

        return sHolder.next;
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
