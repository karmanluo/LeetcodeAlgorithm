package LeetcodeAlgorithm.N23_MergekSortedLists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
            public ListNode mergeKLists(ListNode[] lists) {
            List<Integer> list = new ArrayList<>();
            //存到数组
            for (ListNode curr : lists) {
                while (curr != null) {
                    list.add(curr.val);
                    curr = curr.next;
                }
            }
            //数组排序
            Collections.sort(list);
            //存到链表
            ListNode head = new ListNode(0);
            ListNode curr = head;
            for (int targetNumber : list) {
                ListNode temp = new ListNode(targetNumber);
                curr.next = temp;
                curr = curr.next;
            }
            curr.next = null;
            return head.next;
        }
    }
