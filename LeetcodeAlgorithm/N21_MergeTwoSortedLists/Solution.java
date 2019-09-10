package LeetcodeAlgorithm.N21_MergeTwoSortedLists;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummy;
        while (p != null && q != null){
            if (p.val <= q.val ){
                curr.next = p;
                curr = curr.next;
                p = p.next;
            }else {
                curr.next = q;
                curr = curr.next;
                q = q.next;
            }
        }
        curr.next = q == null ? p:q;
        return dummy.next;
    }
}
