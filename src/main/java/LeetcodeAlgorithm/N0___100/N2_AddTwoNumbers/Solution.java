package LeetcodeAlgorithm.N0___100.N2_AddTwoNumbers;
//You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.
//
// You may assume the two numbers do not contain any leading zero, except the number 0 itself.
//
// Example:
//
//
//Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
//Output: 7 -> 0 -> 8
//Explanation: 342 + 465 = 807.
//
//

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class Solution {
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode p = l1, q = l2, head = new ListNode(0), curr = head, curr1 = new ListNode(0);
        int sum = 0;
        while(p!=null || q!= null){
            sum /= 10;
            if(p!=null){
                sum += p.val;
                p = p.next;
            }
            if (q!=null){
                sum += q.val;
                q = q.next;
            }
            curr.val = sum % 10;
            if(p!=null || q!= null){
                curr.next = new ListNode(0);
            }
            curr1 = curr;
            curr = curr.next;
        }
        if(sum >= 10){
            curr1.next = new ListNode(sum/10);
        }
        return head;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2){
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        node1.next = new ListNode(6);
        node1.next.next = new ListNode(4);
        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(4);
        node2.next.next = new ListNode(5);

        ListNode node3 = new ListNode(0);
        //node3 = addTwoNumbers1( node1,  node2);
        node3 = addTwoNumbers2( node1,  node2);

        while(node3!= null){
            System.out.print(node3.val+ "->");
            node3 = node3.next;
        }
    }
}
