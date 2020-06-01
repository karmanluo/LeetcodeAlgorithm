package LeetcodeAlgorithm.N101_200.N101_SymmetricTree;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || helper(root.left, root.right);
    }

    private boolean helper(TreeNode left, TreeNode right) {
        if (left == null || right == null)  return left == right;
        if (left.val != right.val)  return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}
