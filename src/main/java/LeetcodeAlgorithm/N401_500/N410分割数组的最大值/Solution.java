package LeetcodeAlgorithm.N401_500.N410分割数组的最大值;

import java.util.Arrays;

//官方题解 动态规划
public class Solution {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;

        //f[i][j] 表示将数组的前 i 个数分割为 j 段所能得到的最大连续子数组和的最小值。
        int[][] f = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(f[i], Integer.MAX_VALUE);

        //sub[i] 前i项的和
        int[] sub = new int[n + 1];
        for (int i = 1; i <= n; i++) sub[i] = sub[i - 1] + nums[i - 1];

        f[0][0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m && j <= i; j++) {
                for (int k = 0; k < i; k++) {
                    f[i][j] = Math.min(f[i][j], Math.max(f[k][j - 1], sub[i] - sub[k]));
                }
            }
        }
        return f[n][m];
    }
}
