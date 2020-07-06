package LeetcodeAlgorithm.N101_200.N148排序链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution2 {
    public ListNode quickSort(ListNode h) {
        if (h == null || h.next == null)
            return h;

        /*split into three list*/
        ListNode fakesmall = new ListNode(0), small = fakesmall;
        ListNode fakeequal = new ListNode(0), equal = fakeequal;
        ListNode fakelarge = new ListNode(0), large = fakelarge;

        ListNode cur = h; // pivot is h.
        while (cur != null) {
            if (cur.val < h.val) {
                small.next = cur;
                small = small.next;
            } else if (cur.val == h.val){
                equal.next = cur;
                equal = equal.next;
            }
            else{
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }

        // put an end.
        small.next = equal.next = large.next = null;
        // merge them and return . merge reusing below one. merge for quicksort should be simplified.
        return merge((merge(quickSort(fakesmall.next), quickSort(fakelarge.next))), fakeequal.next);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                node.next = list1;
                list1 = list1.next;
            } else {
                node.next = list2;
                list2 = list2.next;
            }
            node = node.next;
        }
        if (list1 != null) node.next = list1;
        if (list2 != null) node.next = list2;
        return dummy.next;
    }
}
