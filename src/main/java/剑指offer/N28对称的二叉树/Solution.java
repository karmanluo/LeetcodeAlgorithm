package 剑指offer.N28对称的二叉树;

import 剑指offer.数据结构.TreeNode;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)   return true;

        return helper(root.left, root.right);
    }

    private boolean helper(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;

        if (root1 == null || root2 == null) return false;

        return root1.val == root2.val && helper(root1.left, root2.right) && helper(root1.right, root2.left);
    }
}
