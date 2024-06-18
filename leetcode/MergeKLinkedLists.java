import java.util.ArrayList;
import java.util.List;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeKLinkedLists {

    public static void main(String[] args) {
        //[[1,4,5],[1,3,4],[2,6]]
        ListNode one = new ListNode(1);
        one.next = new ListNode(4);
        one.next.next = new ListNode(5);

        ListNode two = new ListNode(1);
        two.next = new ListNode(3);
        two.next.next = new ListNode(4);

        ListNode three = new ListNode(2);
        three.next = new ListNode(6);


        new MergeKLinkedLists().mergeKLists(new ListNode[] {one, two, three});
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        List<ListNode> beforeMerging = new ArrayList<>(lists.length);
        for (ListNode list : lists)
            beforeMerging.add(list);

        while (beforeMerging.size() > 1) {
            List<ListNode> afterMerging = new ArrayList<>(beforeMerging.size());
            for (int i = 0; i < beforeMerging.size(); i += 2) {
                ListNode l1 = beforeMerging.get(i);
                ListNode l2 = i + 1 < beforeMerging.size() ? beforeMerging.get(i + 1) : null;
                afterMerging.add(merge(l1, l2));
            }
            beforeMerging = afterMerging;
        }
        return beforeMerging.get(0);
    }

    private ListNode merge(ListNode one, ListNode two) {
        ListNode dummy = new ListNode();
        ListNode n = dummy;
        while (one != null && two != null) {
            if (one.val < two.val){
                n.next = new ListNode(one.val);
                one = one.next;
            } else {
                n.next = new ListNode(two.val);
                two = two.next;
            }
            n = n.next;
        }
        if (one != null) n.next = one;
        if (two != null) n.next = two;
        return dummy.next;
    }

     public static class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}