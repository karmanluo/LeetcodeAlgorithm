package LeetcodeAlgorithm.N401_500.N494目标和;

/**
 *                   sum(P) - sum(N) = target
 * sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
 *                        2 * sum(P) = target + sum(nums)
 *
 *      Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2
 */

public class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int num : nums) sum += num;

        return sum < S || (sum + S) % 2 > 0 ? 0 : subSum(nums, (S + sum) >>> 1);
    }

    private int subSum(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 0; i < nums.length; i++) {
            for (int j = target; j >= nums[i]; j--) {
                dp[j] = dp[j] + dp[j - nums[i]];
            }
        }

        return dp[target];
    }
}
