package LeetcodeAlgorithm.N0___100.N94_BinaryTreeInorderTraversal;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        InOrder(root, list);
        return list;
    }

    private void InOrder(TreeNode curr, List<Integer> list) {
        if (curr != null){
            InOrder(curr.left, list);
            list.add(curr.val);
            InOrder(curr.right, list);
        }
    }
}
