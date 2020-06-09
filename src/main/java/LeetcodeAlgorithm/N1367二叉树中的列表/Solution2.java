package LeetcodeAlgorithm.N1367二叉树中的列表;

import LeetcodeAlgorithm.N0___100.N19_RemoveNthNodeFromEndofList.ListNode;
import LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public boolean isSubPath(ListNode head, TreeNode root) {
        int[] needle, lps;
        needle = convertLinkedListToArray(head);
        lps = computeKMPTable(needle);
        return kmpSearch(root, 0, needle, lps);
    }

    private boolean kmpSearch(TreeNode root, int i, int[] needle, int[] lps) {
        if (i == needle.length) return true;
        if (root == null) return false;
        while (i > 0 && needle[i] != root.val) i = lps[i - 1];
        if (needle[i] == root.val) i++;
        return kmpSearch(root.left, i, needle, lps) || kmpSearch(root.right, i, needle, lps);
    }

    private int[] computeKMPTable(int[] needle) {
        int n = needle.length;
        int[] lps = new int[n];
        for (int i = 1, j = 0; i < n; i++) {
            while (j > 0 && needle[i] != needle[j]) j = lps[j - 1];
            if (needle[i] == needle[j]) lps[i] = ++j;
        }
        return lps;
    }

    private int[] convertLinkedListToArray(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        int[] arr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }
}