package LeetcodeAlgorithm.N19_RemoveNthNodeFromEndofList;
//Approach 1: Two pass algorithm
public class Solution {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0), first = head;
        dummy.next = head;
        int length = 0;
        while (first != null) {
            length++;
            first = first.next;
        }
        length -= n;
        first = dummy;
        while (length > 0) {
            length--;
            first = first.next;
        }
        first.next = first.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode list = new ListNode(1);
        ListNode p = list;
        p.next = new ListNode(2);
        p = p.next;
        p.next = new ListNode(3);
        p = p.next;
        p.next = new ListNode(4);
        p = p.next;
        p.next = new ListNode(5);
        p = p.next;
        System.out.println("Given linked list: 1->2->3->4->5, and n = 2.结果是：");
        ListNode res = removeNthFromEnd(list, 2);
        while (res != null) {
            System.out.print("->" + res.val);
            res = res.next;
        }
    }
}
