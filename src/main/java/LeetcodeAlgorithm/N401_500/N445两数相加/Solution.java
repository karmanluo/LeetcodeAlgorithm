package LeetcodeAlgorithm.N401_500.N445两数相加;

import LeetcodeAlgorithm.N0___100.N19_RemoveNthNodeFromEndofList.ListNode;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> s1 = new ArrayDeque<>();
        Deque<Integer> s2 = new ArrayDeque<>();

        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode dummy = new ListNode(-1);
        ListNode curr;
        int carry = 0;
        while (carry != 0 || !s1.isEmpty() || !s2.isEmpty()) {
            int x = s1.isEmpty() ? 0 : s1.pop();
            int y = s2.isEmpty() ? 0 : s2.pop();
            int sum = x + y + carry;
            carry = sum / 10;
            curr = new ListNode(sum % 10);
            curr.next = dummy.next;
            dummy.next = curr;
        }

        return dummy.next == null ? null : dummy.next;
    }
}
