package LeetcodeAlgorithm.N199二叉树的右视图;

import 剑指offer.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * 示例:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1, 3, 4]
 * 解释:
 *
 *    1            <---
 *  /   \
 * 2     3         <---
 *  \     \
 *   5     4       <---
 */
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }

    private void helper(List<Integer> res, TreeNode curr, int currDepth) {
        if (curr == null) return;

        if (currDepth == res.size())
            res.add(curr.val);

        helper(res, curr.right, currDepth + 1);
        helper(res, curr.left, currDepth + 1);
    }
}
