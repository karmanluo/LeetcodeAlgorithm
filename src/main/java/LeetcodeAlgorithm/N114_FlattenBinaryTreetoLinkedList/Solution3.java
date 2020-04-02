package LeetcodeAlgorithm.N114_FlattenBinaryTreetoLinkedList;


public class Solution3 {
    public void flatten(TreeNode root) {
        if (root == null) return;

        TreeNode left = root.left;
        TreeNode right = root.right;

        root.left = null;

        flatten(left);
        flatten(right);

        root.right = left;
        TreeNode curr = root;
        while (curr.right != null) curr = curr.right;
        curr.right = right;
    }
}
