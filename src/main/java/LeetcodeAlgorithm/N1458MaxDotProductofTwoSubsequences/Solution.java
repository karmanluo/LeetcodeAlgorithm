package LeetcodeAlgorithm.N1458MaxDotProductofTwoSubsequences;

import java.util.Arrays;

public class Solution {
    public int maxDotProduct(int[] nums1, int[] nums2) {

        //注意：本道题 maxDotProduct 最大点乘，必须是自己算出来的值。
        //所以[-1, -1], [1, 1]这个题算出来的值为 -1; 而不能是 0
        //所以为了避免这种情况的发生，需要对数组做一个初始化，初始化的时候将所有的值变成负值

        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 0; i <= len1; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE / 2);
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                int case1 = dp[i - 1][j];
                int case2 = dp[i][j - 1];
                int case3 = dp[i - 1][j - 1] + nums1[i - 1] * nums2[j - 1];
                int case4 = nums1[i - 1] * nums2[j - 1];

                dp[i][j] = Math.max(case1, Math.max(case2, Math.max(case3, case4)));
            }
        }

        return dp[len1][len2];
    }
}
