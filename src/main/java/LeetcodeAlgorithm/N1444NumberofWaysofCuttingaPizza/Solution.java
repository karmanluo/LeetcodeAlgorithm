package LeetcodeAlgorithm.N1444NumberofWaysofCuttingaPizza;

public class Solution {
    private int MOD = 1_000_000_007, m, n;
    private Integer dp[][][];
    private int preSum[][];

    public int ways(String[] pizza, int k) {
        m = pizza.length;
        n = pizza[0].length();
        dp = new Integer[k][m][n];
        preSum = new int[m + 1][n + 1];

        //这个直接就是以为i,j为左上角的时候，其和为多少
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                preSum[i][j] = preSum[i][j + 1] + preSum[i + 1][j] - preSum[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
            }
        }

        return dfs(0, 0, k - 1);
    }

    private int dfs(int x, int y, int cut) {
        if (cut == 0 && preSum[x][y] > 0)   return 1;
        if (preSum[x][y] == 0)  return 0;

        if (dp[cut][x][y] != null)  return dp[cut][x][y];

        int ans = 0;
        //Cut horizontal
        for (int i = x + 1; i < m; i++) {
            if (preSum[x][y] - preSum[i][y] > 0){
                ans = (ans + dfs(i, y, cut - 1)) % MOD;
            }
        }

        //Cut vertical
        for (int j = y + 1; j < n; j++) {
            if (preSum[x][y] - preSum[x][j] > 0){
                ans = (ans + dfs(x, j, cut - 1)) % MOD;
            }
        }

        dp[cut][x][y] = ans;
        return ans;
    }
}
