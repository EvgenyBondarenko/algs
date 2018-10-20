/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * Add the two numbers and return it as a linked list.
 * <p>
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * <p>
 * Example:
 * <p>
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);
        l1.next.next.next = new ListNode(1);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        new AddTwoNumbers().new Solution().addTwoNumbers(l1, l2);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    class Solution {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode res = null;
            ListNode cur = null;
            boolean carryOn = false;
            while (!(l1 == null && l2 == null && !carryOn)) {
                int val1 = l1 == null ? 0 : l1.val;
                int val2 = l2 == null ? 0 : l2.val;
                int sum = val1 + val2;
                if (carryOn) sum++;
                if (sum >= 10) {
                    sum = sum - 10;
                    carryOn = true;
                } else carryOn = false;

                if (res == null) {
                    res = new ListNode(sum);
                    cur = res;
                } else {
                    cur.next = new ListNode(sum);
                    cur = cur.next;
                }

                if (l1 != null) l1 = l1.next;
                if (l2 != null) l2 = l2.next;
            }
            return res;
        }
    }
}
