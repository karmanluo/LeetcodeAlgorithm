package LeetcodeAlgorithm.N101_200.N105_从前序与中序构造二叉树;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int prelen = preorder.length, inlen = inorder.length;
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inlen; i++) {
            inMap.put(inorder[i], i);
        }

        return buildTree(preorder, 0, prelen - 1, inMap, 0, inlen - 1);
    }

    //leetcode 官方视频题解
    public TreeNode buildTree(int[] preorder, int preStart, int preEnd, Map<Integer, Integer> inMap, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int preVal = preorder[preStart];
        int inIndex = inMap.get(preVal);

        root.left = buildTree(preorder, preStart + 1, preStart + inIndex - inStart, inMap, inStart, inIndex - 1);
        root.right = buildTree(preorder, preStart + inIndex - inStart + 1, preEnd, inMap, inIndex + 1, inEnd);

        return root;
    }
}
