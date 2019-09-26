package LeetcodeAlgorithm.N53_MaximumSubarray;
/*
*dp 表示以nums[i]结尾的最大相邻序列之和
*
* dp空间复杂度优化，space complexity o(1)就可以
* */
public class Solution2 {
    public int maxSubArray(int[] nums) {
        int dp = nums[0];
        int max = dp;

        for (int i = 1; i < nums.length; i++) {
            dp = (dp > 0 ? dp : 0) + nums[i];
            max = Math.max(dp, max);
        }
        return max;
    }
}
