package LeetcodeAlgorithm.N0___100.N54螺旋矩阵;

import java.util.ArrayList;
import java.util.List;

public class Solution1 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int x = 0, y = 0, d = 1;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] cache = new boolean[m][n];

        for (int i = 0; i < m * n; i++) {
            res.add(matrix[x][y]);
            cache[x][y] = true;

            int a = x + dx[d], b = y + dy[d];
            if (a < 0 || a >= m || b < 0 || b >= n || cache[a][b]) {
                d = (d + 1) % 4;
                a = x + dx[d];
                b = y + dy[d];
            }
            x = a;
            y = b;
        }

        return res;
    }
}
