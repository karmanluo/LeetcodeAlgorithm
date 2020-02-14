package 剑指offer.N06从尾到头打印链表;

import LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs.ListNode;

public class Solution2 {
    public int[] reversePrint(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null){
            len++;
            curr = curr.next;
        }
        int[] arr = new int[len];
        for (int i = len - 1; i >= 0 ; i--) {
            arr[i] = head.val;
            head = head.next;
        }
        return arr;
    }
}
