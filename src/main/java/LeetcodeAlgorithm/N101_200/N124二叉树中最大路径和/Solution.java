package LeetcodeAlgorithm.N101_200.N124二叉树中最大路径和;

import LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal.TreeNode;

public class Solution {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int leftSum = Math.max(dfs(root.left), 0);
        int rightSum = Math.max(dfs(root.right), 0);

        int nodeSum = leftSum + rightSum + root.val;
        maxSum = Math.max(maxSum, nodeSum);

        return root.val + Math.max(leftSum, rightSum);
    }
}