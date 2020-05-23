package 剑指offer.N06从尾到头打印链表;

import LeetcodeAlgorithm.N0___100.N24_SwapNodesInPairs.ListNode;

import java.util.Stack;

public class Solution {
    public int[] reversePrint(ListNode head) {
        if (head == null)   return new int[0];

        Stack<Integer> stack = new Stack<>();

        while (head != null){
            stack.push(head.val);
            head = head.next;
        }

        int n = stack.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = stack.pop();
        }
        return arr;
    }
}
