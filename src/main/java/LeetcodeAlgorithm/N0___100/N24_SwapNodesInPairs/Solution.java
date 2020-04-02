package LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs;

/**
 * 递归
 */
public class Solution {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null)  return head;
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        return n;
    }
}
