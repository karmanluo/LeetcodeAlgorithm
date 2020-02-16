package 剑指offer.N13机器人的运动范围;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 示例 1：
 *
 * 输入：m = 2, n = 3, k = 1
 * 输出：3
 * 示例 1：
 *
 * 输入：m = 3, n = 1, k = 0
 * 输出：1
 * 提示：
 *
 * 1 <= n,m <= 100
 * 0 <= k <= 20
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
class Solution {
    public static int movingCount(int m, int n, int k) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{0,0});
        int[][] visited = new int[m][n];
        int count = 0;
        while(!q.isEmpty()){
            int[] point = q.poll();
            int x = point[0];
            int y = point[1];
            if(visited[x][y] == 0){
                visited[x][y] = 1;
                int sum_x = 0, sum_y = 0, sum = 0;
                sum_x = x / 10 + x % 10;
                sum_y = y / 10 + y % 10;
                sum = sum_x + sum_y;
                if(sum <= k){
                    count++;
                    if(y + 1 < n && visited[x][y + 1] == 0)
                        q.add(new int[]{x, y + 1});
                    if(x + 1 < m && visited[x + 1][y] == 0)
                        q.add(new int[]{x + 1, y});
                    if(y - 1 >= 0 && visited[x][y - 1] == 0)
                        q.add(new int[]{x, y - 1});
                    if(x - 1 >= 0 && visited[x - 1][y] == 0)
                        q.add(new int[]{x - 1, y});
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(movingCount(3, 3, 4));
    }
}
