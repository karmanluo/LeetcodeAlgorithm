package LeetcodeAlgorithm.N201_300.N234回文链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public boolean isPalindrome(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        //奇数的时候需要将slow指针右移
        if (fast != null){
            slow = slow.next;
        }
        //翻转少的一半
        slow = reverse(slow);
        fast = head;

        while (slow != null){
            if (slow.val != fast.val)
                return false;
            slow = slow.next;
            fast = fast.next;
        }
        return true;
    }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null){
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
