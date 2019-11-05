package LeetcodeAlgorithm.N114_FlattenBinaryTreetoLinkedList;

public class Solution2 {
    private TreeNode prev = null;

    private void flatten(TreeNode root) {
        if (root == null) return;

        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
