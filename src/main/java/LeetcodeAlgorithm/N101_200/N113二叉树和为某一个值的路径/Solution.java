package LeetcodeAlgorithm.N101_200.N113二叉树和为某一个值的路径;

import 剑指offer.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/16 17:16
 */
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> tmp = new ArrayList<Integer>();
        pathSum(root, sum, tmp, res);
        return res;
    }

    private void pathSum(TreeNode node, int sum, List<Integer> tmp, List<List<Integer>> res) {
        if (node == null)   return;

        tmp.add(node.val);
        if (node.left == null && node.right == null && node.val == sum)
            res.add(new ArrayList(tmp));
        else{
            pathSum(node.left, sum - node.val, tmp, res);
            pathSum(node.right, sum - node.val, tmp, res);
        }

        tmp.remove(tmp.size() - 1);//移除最后一个元素
    }

}