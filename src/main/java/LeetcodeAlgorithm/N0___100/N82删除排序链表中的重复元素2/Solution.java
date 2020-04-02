package LeetcodeAlgorithm.N0___100.N82删除排序链表中的重复元素2;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        //use two pointers, slow - track the node before the dup nodes,
        // fast - to find the last node of dups.
        ListNode slow = dummy, fast = head;
        slow.next = fast;
        while (fast != null) {
            while (fast.next != null && fast.val == fast.next.val)
                fast = fast.next;
            //不等说明有重
            if (slow.next != fast) {
                slow.next = fast.next;
                //fast到新的位置去看看新的元素是否有问题
                fast = slow.next;
            }else{
                slow = slow.next;
                fast = fast.next;
            }
        }
        return dummy.next;
    }
}
