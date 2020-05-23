package LeetcodeAlgorithm.N987二叉树的垂序遍历;

import 剑指offer.数据结构.TreeNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/7 22:40
 */
public class Solution {
    List<int[]> list = new ArrayList<>();
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        DFS(root, 0, 0);

        list.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[1] != b[1]){
                    //根据x排序
                    return a[1] - b[1];
                }else if (a[2] != b[2]){
                    //根据y递减的速度
                    return -a[2] + b[2];
                }else {
                    //在同一位置按照值从小到大排序
                    return a[0] - b[0];
                }
            }
        });

        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            int j = i; //找到可以加到一起的边界,i代表左边界，j代表右边界的下一个

            while(j < list.size() && list.get(i)[1] == list.get(j)[1]) j++;

            List<Integer> tmp = new ArrayList<>();
            for (int k = i; k < j; k++) tmp.add(list.get(i)[0]);
            res.add(tmp);

            i = j - 1;
        }

        return res;
    }

    private void DFS(TreeNode node, int x, int y){
        if(node == null)    return;
        list.add(new int[]{node.val, x, y});
        DFS(node.left, x - 1, y - 1);
        DFS(node.right, x + 1, y - 1);
    }
}
