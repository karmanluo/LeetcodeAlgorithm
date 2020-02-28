package LeetcodeAlgorithm.N0___100.N54螺旋矩阵;

import java.util.LinkedList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new LinkedList<>();
        if (matrix.length == 0) return list;

        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0, down = m - 1, left = 0, right = n - 1;
        //记住下面这段，很关键，，，如何遍历
        while (true) {
            for (int i = left; i <= right; i++)
                list.add(matrix[up][i]);
            if (++up > down) break;
            for (int j = up; j <= down; j++)
                list.add(matrix[j][right]);
            if (--right < left) break;
            for (int k = right; k >= left; k--)
                list.add(matrix[down][k]);
            if (--down < up) break;
            for (int l = down; l >= up; l--)
                list.add(matrix[l][left]);
            if (++left > right) break;
        }
        return list;
    }
}
