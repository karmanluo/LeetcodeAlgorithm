package LeetcodeAlgorithm.N101_200.N101_SymmetricTree;

public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;

        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode l, TreeNode r) {
        if (l == null || r == null) return r == l;
        if (l.val != r.val) return false;

        return isSymmetric(l.left, r.right) && isSymmetric(l.right, r.left);
    }

}