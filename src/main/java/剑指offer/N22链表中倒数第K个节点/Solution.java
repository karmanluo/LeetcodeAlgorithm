package 剑指offer.N22链表中倒数第K个节点;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode frontNode = head, behindNode = head;
        while (frontNode != null && k > 0){
            frontNode = frontNode.next;
            k--;
        }
        if (k > 0) return null;
        while (frontNode != null){
            frontNode = frontNode.next;
            behindNode = behindNode.next;
        }
        return behindNode;
    }
}
