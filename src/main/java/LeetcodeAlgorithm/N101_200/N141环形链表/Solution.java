package LeetcodeAlgorithm.N101_200.N141环形链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;

        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
