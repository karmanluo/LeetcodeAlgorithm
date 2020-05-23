package 剑指offer.N25合并两个排序的链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode pre = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
                pre = pre.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
                pre = pre.next;
            }
        }


        if (l2 != null) l1 = l2;
        if (l1 != null) {
            pre.next = l1;
        }
        return dummy.next;
    }
}
