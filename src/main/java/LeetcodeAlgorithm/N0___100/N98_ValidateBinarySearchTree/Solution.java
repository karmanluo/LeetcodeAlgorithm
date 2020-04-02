package LeetcodeAlgorithm.N0___100.N98_ValidateBinarySearchTree;

import java.util.Stack;

public class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode pre = null;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (pre != null && curr.val <= pre.val) {
                return false;
            } else {
                pre = curr;
            }
            curr = curr.right;
        }
        return true;
    }
}
