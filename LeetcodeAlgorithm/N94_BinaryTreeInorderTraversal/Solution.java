package LeetcodeAlgorithm.N94_BinaryTreeInorderTraversal;
//Given a binary tree, return the inorder traversal of its nodes' values.
//
// Example:
//Input: [1,null,2,3]
//   1
//    \
//     2
//    /
//   3
//
//Output: [1,3,2]
//
// Follow up: Recursive solution is trivial, could you do it iteratively?
// Related Topics Hash Table Stack Tree
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<Integer>();

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
        return list;
    }
}
