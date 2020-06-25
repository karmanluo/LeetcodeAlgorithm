package LeetcodeAlgorithm.N401_500.N407接雨水;

import java.util.PriorityQueue;

public class Solution {
    int res = 0;
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int trapRainWater(int[][] heightMap) {
        int m = heightMap.length;
        if (m < 3) return 0;
        int n = heightMap[0].length;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            minHeap.offer(new int[]{i, 0, heightMap[i][0]});
            minHeap.offer(new int[]{i, n - 1, heightMap[i][n - 1]});
        }
        for (int j = 0; j < n; j++) {
            minHeap.offer(new int[]{0, j, heightMap[0][j]});
            minHeap.offer(new int[]{m - 1, j, heightMap[m - 1][j]});
        }

        while (!minHeap.isEmpty()) {
            int[] poll = minHeap.poll();
            for (int[] dir : dirs) {
                int x = poll[0] + dir[0];
                int y = poll[1] + dir[1];
                if (x <= 0 || y <= 0 || x >= m - 1 || y >= n - 1 || visited[x][y]) continue;
                if (poll[2] > heightMap[x][y]) {
                    res += poll[2] - heightMap[x][y];
                }
                minHeap.offer(new int[]{x, y, Math.max(heightMap[x][y], poll[2])});
                visited[x][y] = true;
            }
        }
        return res;
    }
}
