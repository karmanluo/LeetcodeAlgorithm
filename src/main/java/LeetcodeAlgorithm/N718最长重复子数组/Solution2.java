package LeetcodeAlgorithm.N718最长重复子数组;

public class Solution2 {
    public int findLength(int[] A, int[] B) {
        int m = A.length, n = B.length;
        if (m == 0 || n == 0) return 0;

        int[] dp = new int[n];
        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (A[i] == B[j]) {
                    dp[j] = (j > 0 ? dp[j - 1] : 0) + 1;
                    max = Math.max(dp[j], max);
                } else dp[j] = 0;
            }
        }
        return max;
    }
}
