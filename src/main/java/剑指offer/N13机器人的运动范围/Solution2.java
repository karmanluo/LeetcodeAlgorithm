package 剑指offer.N13机器人的运动范围;

import java.util.Stack;

public class Solution2 {
    //DFS
    public int movingCount(int m, int n, int k) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{0, 0});
        int count = 0;
        boolean[][] visited = new boolean[m][n];
        while (!stack.isEmpty()){
            int[] point = stack.pop();
            int x = point[0];
            int y = point[1];
            if (!visited[x][y]){
                visited[x][y] = true;
                int sum_x = x % 10 + x / 10;
                int sum_y = y % 10 + y / 10;
                if ((sum_x + sum_y) <= k){
                    count++;
                    if ((x + 1) < m && !visited[x + 1][y])
                        stack.push(new int[]{x + 1, y});
                    if ((y + 1) < n && !visited[x][y + 1])
                        stack.push(new int[]{x, y + 1});
                    if ((x - 1) >= 0 && !visited[x - 1][y])
                        stack.push(new int[]{x - 1, y});
                    if ((y - 1) >= 0 && !visited[x][y - 1])
                        stack.push(new int[]{x, y - 1});
                }
            }
        }
        return count;
    }
}
