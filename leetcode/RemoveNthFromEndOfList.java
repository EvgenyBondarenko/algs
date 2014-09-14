public class RemoveNthFromEndOfList {

    public static void main(String[] args) {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        one.next = two;
        two.next = three;
        three.next = four;
        System.out.println(new RemoveNthFromEndOfList().new Solution().removeNthFromEnd(one, 1));

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public class Solution {
        public ListNode removeNthFromEnd(ListNode head, int n) {
            if (head.next == null && n == 1) {
                return null;
            }
            ListNode last = head;
            ListNode toDelete = head;
            ListNode beforeToDelete = head;
            for (int i = 1; i < n; i++) {
                last = last.next;
            }
            while (last.next != null) {
                last = last.next;
                beforeToDelete = toDelete;
                toDelete = toDelete.next;
            }
            if (toDelete == head) {
                return head.next;
            }
            beforeToDelete.next = toDelete.next;
            return head;
        }
    }
}
