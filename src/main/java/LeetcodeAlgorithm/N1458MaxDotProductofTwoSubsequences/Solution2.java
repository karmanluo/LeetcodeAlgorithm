package LeetcodeAlgorithm.N1458MaxDotProductofTwoSubsequences;

import java.util.Arrays;

public class Solution2 {
    public int maxDotProduct(int[] nums1, int[] nums2) {

        //注意：本道题 maxDotProduct 最大点乘，必须是自己算出来的值。
        //所以[-1, -1], [1, 1]这个题算出来的值为 -1; 而不能是 0

        int len1 = nums1.length;
        int len2 = nums2.length;

        int[][] dp = new int[len1][len2];

        for (int i = 0; i < len1; i++) {
            for (int j = 0; j < len2; j++) {
                dp[i][j] = nums1[i] * nums2[j];
                if (i > 0 && j > 0) dp[i][j] += Math.max(0, dp[i - 1][j - 1]);
                if (i > 0)  dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                if (j > 0)  dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
            }
        }

        return dp[len1 - 1][len2 - 1];
    }
}
