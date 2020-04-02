package 剑指offer.N18删除链表的节点;

public class Solution {
    public ListNode deleteNode(ListNode head, int val) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        while (pre.next != null){
            ListNode curr = pre.next;
            if (curr.val == val){
                pre.next = curr.next;
                return dummy.next;
            }
            pre = curr;
        }
        return dummy.next;
    }
}
