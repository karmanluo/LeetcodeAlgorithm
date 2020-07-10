package LeetcodeAlgorithm.N301_400.N309最佳买卖股票时机含冷冻期;

public class Solution {
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len == 0) return 0;
        int[][] dp = new int[len][3];

        //dp[i][0] 表示当天之后 是 持有 状态的最大收益
        //dp[i][1] 表示当天之后 是 不持有冷静期 状态的最大收益
        //dp[i][2] 表示当天之后 是 不持有过冷静期 状态的最大收益
        dp[0][0] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][2] - prices[i], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
        }

        return Math.max(dp[len - 1][1], dp[len - 1][2]);
    }
}