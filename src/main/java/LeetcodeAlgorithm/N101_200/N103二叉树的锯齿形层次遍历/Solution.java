package LeetcodeAlgorithm.N101_200.N103二叉树的锯齿形层次遍历;

import LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null) return res;
        queue.offer(root);

        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);

                if (flag) list.addLast(node.val);
                else list.addFirst(node.val);
            }
            flag = flag == true ? false : true;
            res.add(list);
        }

        return res;
    }
}
