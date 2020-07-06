package LeetcodeAlgorithm.N1143最长公共子序;

public class Solution2 {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[] dp = new int[n + 1];

        for (int i = 1; i <= m; i++) {
            int tmp = 0;
            for (int j = 1; j <= n; j++) {
                int pre = tmp;
                tmp = dp[j];
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) dp[j] = pre + 1;
                else dp[j] = Math.max(dp[j - 1], dp[j]);
            }
        }

        return dp[n];
    }
}
