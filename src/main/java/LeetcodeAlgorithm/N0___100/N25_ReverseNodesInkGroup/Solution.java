package LeetcodeAlgorithm.N0___100.N25_ReverseNodesInkGroup;

import LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs.ListNode;

/**
 * 递归方法
 */
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode curr = head;
        int count = 0;
        while (curr != null && count!= k){// find the k+1 node
            curr = curr.next;
            count++;
        }

        if (count == k){// if k+1 node is found
            curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
            // head - head-pointer to direct part,
            // curr - head-pointer to reversed part;
            while (count-- > 0){    // reverse current k-group:  这里count先比较再-1
                ListNode tmp = head.next; // tmp - next head in direct part
                head.next = curr; // preappending "direct" head to the reversed list
                curr = head; // move head of reversed part to a new node
                head = tmp; // move "direct" head to the next node in direct part
            }
            head = curr;
        }
        return head;
    }
}
