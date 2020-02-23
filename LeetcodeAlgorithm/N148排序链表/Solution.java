package LeetcodeAlgorithm.N148排序链表;

import 剑指offer.N18删除链表的节点.ListNode;

public class Solution {
    public ListNode mergeSort(ListNode head) {
        if(head == null || head.next == null)   return head;

        // step 1. cut the list to two halves
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode list2 = slow.next;
        slow.next = null;

        // step 2. sort each half
        ListNode l1 = mergeSort(head);
        ListNode l2 = mergeSort(list2);

        // step 3. merge l1 and l2
        return merge(l1, l2);
    }

    private ListNode merge(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode node = dummy;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                node.next = list1;
                list1 = list1.next;
            }else {
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
