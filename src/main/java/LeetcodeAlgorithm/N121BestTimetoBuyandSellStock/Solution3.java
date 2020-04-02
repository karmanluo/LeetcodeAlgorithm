package LeetcodeAlgorithm.N121BestTimetoBuyandSellStock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/9 20:36
 */
public class Solution3 {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        if(n == 0)  return 0;
        int dp_i_0 = 0, dp_i_1 = -prices[0];

        for (int i = 1; i < n; i++) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
            dp_i_1 = Math.max(dp_i_1, -prices[i]);
        }

        return dp_i_0;
    }
}
