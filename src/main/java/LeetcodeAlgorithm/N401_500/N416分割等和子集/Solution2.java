package LeetcodeAlgorithm.N401_500.N416分割等和子集;

public class Solution2 {
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int len = nums.length;
        int sum = 0;
        for (int i = 0; i < len; i++) sum += nums[i];
        if ((sum & 1) == 1) return false;
        int target = sum >>> 1;

        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int i = 1; i <= len; i++) {
            for (int j = target; j >= nums[i - 1]; j--) {
                if (dp[target] == true) return true;
                dp[j] = dp[j] || dp[j - nums[i - 1]];
            }
        }

        return dp[target];
    }
}
