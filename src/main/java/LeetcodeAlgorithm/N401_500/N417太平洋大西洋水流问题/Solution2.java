package LeetcodeAlgorithm.N401_500.N417太平洋大西洋水流问题;

import java.util.*;

public class Solution2 {
    int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        if (matrix == null || matrix.length == 0) return res;

        int m = matrix.length, n = matrix[0].length;
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        Queue<int[]> pQueue = new LinkedList<>();
        Queue<int[]> aQueue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            pQueue.offer(new int[]{i, 0});
            aQueue.offer(new int[]{i, n - 1});
            pac[i][0] = true;
            atl[i][n - 1] = true;
        }
        for (int i = 0; i < n; i++) {
            pQueue.offer(new int[]{0, i});
            aQueue.offer(new int[]{m - 1, i});
            pac[0][i] = true;
            atl[m - 1][i] = true;
        }
        bfs(matrix, pQueue, pac);
        bfs(matrix, aQueue, atl);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pac[i][j] && atl[i][j])
                    res.add(Arrays.asList(i, j));
            }
        }

        return res;
    }

    private void bfs(int[][] matrix, Queue<int[]> queue, boolean[][] visited) {
        int m = matrix.length, n = matrix[0].length;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] d : dir) {
                int x = curr[0] + d[0];
                int y = curr[1] + d[1];
                if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y] ||
                       matrix[x][y] < matrix[curr[0]][curr[1]]) {
                    continue;
                }
                visited[x][y] = true;
                queue.offer(new int[]{x, y});
            }
        }
    }
}
