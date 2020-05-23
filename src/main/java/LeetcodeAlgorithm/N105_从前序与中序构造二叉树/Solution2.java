package LeetcodeAlgorithm.N105_从前序与中序构造二叉树;


import java.util.HashMap;
import java.util.Map;

class Solution2 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();

        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }

        return helper(0, 0, inorder.length - 1, preorder, inorder, inMap);
    }

    private TreeNode helper(int preStart, int inStart, int inEnd, int[] preorder, int[] inorder, Map<Integer, Integer> inMap) {
        if (preStart > preorder.length - 1 || inStart > inEnd) return null;

        TreeNode root = new TreeNode(preorder[preStart]);
        int inIndex;
        inIndex = inMap.get(root.val);
        root.left = helper(preStart + 1, inStart, inIndex - 1, preorder, inorder, inMap);
        root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder, inMap);
        return root;
    }
}