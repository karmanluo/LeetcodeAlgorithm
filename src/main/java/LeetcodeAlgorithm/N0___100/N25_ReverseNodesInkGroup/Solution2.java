package LeetcodeAlgorithm.N0___100.N25_ReverseNodesInkGroup;

import LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs.ListNode;

/**
 * non-recursive solution
 */
public class Solution2 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1)
            return head;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode begin = dummyHead;
        int i = 0;
        while (head != null) {
            i++;
            if (i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            } else {
                head = head.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode prev = begin;
        ListNode first = curr;
        ListNode next;
        while (curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        first.next = end;
        begin.next = prev;
        return first;
    }
}
