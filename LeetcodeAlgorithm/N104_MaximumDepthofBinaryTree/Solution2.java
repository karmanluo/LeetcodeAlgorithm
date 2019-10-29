package LeetcodeAlgorithm.N104_MaximumDepthofBinaryTree;

import java.util.Stack;

public class Solution2 {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;

        Stack<TreeNode> stackNode = new Stack<>();
        Stack<Integer> stackDepth = new Stack<>();
        int max = 0;

        stackNode.push(root);
        stackDepth.push(1);
        while (!stackNode.isEmpty()) {
            TreeNode cur = stackNode.pop();
            int temp = stackDepth.pop();
            max = Math.max(temp, max);
            if (cur.left != null) {
                stackNode.push(cur.left);
                stackDepth.push(temp + 1);
            }
            if (cur.right != null) {
                stackNode.push(cur.right);
                stackDepth.push(temp + 1);
            }

        }
        return max;
    }
}
