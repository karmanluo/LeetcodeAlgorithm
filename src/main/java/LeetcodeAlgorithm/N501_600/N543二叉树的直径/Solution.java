package LeetcodeAlgorithm.N501_600.N543二叉树的直径;

import LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal.TreeNode;

public class Solution {
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);

        return max == 0 ? 0 : max - 1;
    }

    private int helper(TreeNode root) {
        if (root == null) return 0;

        int lDeep = helper(root.left);
        int rDeep = helper(root.right);
        max = Math.max(max, lDeep + rDeep + 1);

        return 1 + Math.max(lDeep, rDeep);
    }
}