package LeetcodeAlgorithm.N121BestTimetoBuyandSellStock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/9 20:17
 */
public class Solution2 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)  return 0;
        int[][] dp = new int[n][2];

        for (int i = 0; i < n; i++) {
            if (i - 1 == -1){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }

        return dp[n - 1][0];
    }
}