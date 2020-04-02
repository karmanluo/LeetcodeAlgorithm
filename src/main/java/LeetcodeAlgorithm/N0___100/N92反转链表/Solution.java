package LeetcodeAlgorithm.N0___100.N92反转链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = dummy, pre = null;
        for (int i = 0; i < m; i++) {
            pre = curr;
            curr = curr.next;
        }
        ListNode firstTail = pre;
        ListNode reverseTail = curr;
        for (int i = m; i <= n; i++) {
            ListNode nextNode = curr.next;
            curr.next = pre;
            pre = curr;
            curr = nextNode;
        }
        firstTail.next = pre;
        reverseTail.next = curr;
        return dummy.next;
    }
}
