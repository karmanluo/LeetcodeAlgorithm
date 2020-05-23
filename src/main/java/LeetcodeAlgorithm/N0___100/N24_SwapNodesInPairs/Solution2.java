package LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs;

public class Solution2 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode current = dummy;
        while (current.next != null && current.next.next != null) {
            ListNode first = current.next;
            ListNode second = current.next.next;
            //交换位置
            first.next = second.next;
            current.next = second;
            second.next = first;
            current = current.next.next;
        }
        return dummy.next;
    }
}
