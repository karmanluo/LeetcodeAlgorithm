package LeetcodeAlgorithm.N301_400.N322零钱兑换;

import java.util.Arrays;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/8 19:21
 */
public class Solution2 {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i)
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}
