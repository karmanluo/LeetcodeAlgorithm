package LeetcodeAlgorithm.N19_RemoveNthNodeFromEndofList;

public class Soulution2 {
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode start = new ListNode(0);
        ListNode slow = start, fast = start;
        fast.next = head;
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return start.next;
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
