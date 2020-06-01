package LeetcodeAlgorithm.N101_200.N114_FlattenBinaryTreetoLinkedList;

import java.util.Stack;

//Given a binary tree, flatten it to a linked list in-place.
//
// For example, given the following tree:
//    1
//   / \
//  2   5
// / \   \
//3   4   6
// The flattened tree should look like:
//1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6
//
// Related Topics Tree Depth-first Search
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
            if (!stack.isEmpty()){
                curr.right = stack.peek();
            }
            curr.left = null;
        }
    }
}
