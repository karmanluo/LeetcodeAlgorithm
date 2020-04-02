package 剑指offer.N26树的子结构;

import 剑指offer.数据结构.TreeNode;

public class Solution {
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        if (A == null || B == null) return false;

        if (A != null && B != null && A.val == B.val){
            return helper(A, B);
        }

        if (isSubStructure(A.left, B))  return true;
        if (isSubStructure(A.right, B)) return true;

        return false;
    }

    private boolean helper(TreeNode a, TreeNode b) {
        if (b == null) return true;

        if (a != null && b!= null && a.val != b.val)
            return false;
        if (a != null && b!= null && a.val == b.val)
            return helper(a.left, b.left) && helper(a.right, b.right);
        return false;
    }
}
