package Lintcode.N596二叉树最长连续序列;

import 剑指offer.数据结构.TreeNode;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/16 23:17
 */
public class Solution {
    /**
     * @param root: the root of binary tree
     * @return: the length of the longest consecutive sequence path
     */
    public int longestConsecutive(TreeNode root) {
        // write your code here
        return helper(root, null, 0);
    }

    private int helper(TreeNode root, TreeNode parent, int lengthWithoutRoot) {
        if (root == null) return 0;

        int length = (parent != null && parent.val + 1 == root.val)
                ? lengthWithoutRoot + 1 : 1;

        int left = helper(root.left, root, length);
        int right = helper(root.right, root, length);

        return Math.max(length, Math.max(left, right));
    }
}
