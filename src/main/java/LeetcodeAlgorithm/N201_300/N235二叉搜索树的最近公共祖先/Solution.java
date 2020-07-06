package LeetcodeAlgorithm.N201_300.N235二叉搜索树的最近公共祖先;

import LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal.TreeNode;

//注意：二叉搜索树又名二叉排序树，其是有序的
public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;

        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}