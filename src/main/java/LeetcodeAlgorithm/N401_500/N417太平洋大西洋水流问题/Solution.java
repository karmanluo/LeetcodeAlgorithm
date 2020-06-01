package LeetcodeAlgorithm.N401_500.N417太平洋大西洋水流问题;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        int m = matrix.length;
        if (m == 0) {
            return res;
        }

        int n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1 ,0}};
        for (int i = 0; i < m; i++) {
            dfs(matrix, dir, pac, i, 0);    //第一列
            dfs(matrix, dir, atl, i, n - 1);    //最后一列
        }

        for (int i = 0; i < n; i++) {
            dfs(matrix, dir, pac, 0, i);        //  第一行
            dfs(matrix, dir, atl, m - 1, i);    //  最后一行
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j]) {
                    res.add(Arrays.asList(i, j));
                }
            }
        }

        return res;
    }

    private void dfs(int[][] matrix, int[][] dir, boolean[][] visited, int i, int j) {
        int m = matrix.length, n = matrix[0].length;
        visited[i][j] = true;
        for (int[] d : dir) {
            int x = i + d[0];
            int y = j + d[1];
            if (x < 0 || y < 0 || x >= m || y >= n || visited[x][y] || matrix[i][j] > matrix[x][y]) {
                continue;
            }
            dfs(matrix, dir, visited, x, y);
        }
    }
}
