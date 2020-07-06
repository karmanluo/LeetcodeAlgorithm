package LeetcodeAlgorithm.N401_500.N416分割等和子集;

public class Solution {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) sum += nums[i];
        if ((sum & 1) == 1) return false;
        int target = sum >>> 1;

        boolean[][] dp = new boolean[len + 1][target + 1];
        dp[0][0] = true;

        for (int i = 1; i <= len; i++) dp[i][0] = true;
        for (int j = 1; j <= target; j++) dp[0][j] = false;

        for (int i = 1; i <= len; i++) {
            for (int j = 1; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= nums[i - 1]) dp[i][j] = dp[i - 1][j] || dp[i - 1][j - nums[i - 1]];
            }
        }

        return dp[len][target];
    }
}
