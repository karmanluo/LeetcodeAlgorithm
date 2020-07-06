package LeetcodeAlgorithm.N1449FormLargestIntegerWithDigitsThatAdduptoTarget;

public class Solution {
    public String largestNumber(int[] cost, int target) {
        if(target == 0) return "";
        int[] dp = new int[target + 1];

        //baseCase dp[0] = 0, 其他地方的值，应该从dp[0]开始
        //如果不是从dp[0]开始，那么其值总会为负数
        for (int t = 1; t <= target; t++) {
            dp[t] = Integer.MIN_VALUE;      //这里很关键，思考下为-1会出现什么情况？
            for (int i = 0; i < 9; i++) {
                if (t >= cost[i]){
                    dp[t] = Math.max(dp[t], 1 + dp[t - cost[i]]);
                }
            }
        }

        if (dp[target] < 0){
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 8; i >= 0; i--) {
            while (target >= cost[i] && dp[target] == dp[target - cost[i]] + 1){
                sb.append(i + 1);
                target -= cost[i];
            }
        }

        return sb.toString();
    }
}
