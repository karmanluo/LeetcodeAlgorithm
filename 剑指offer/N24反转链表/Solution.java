package 剑指offer.N24反转链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode curr = head;

        head = null;
        while (curr != null){
            ListNode insertNode = curr;
            curr = curr.next;
            dummy.next = insertNode;
            insertNode.next = head;
            head = insertNode;
        }
        return dummy.next;
    }
}
