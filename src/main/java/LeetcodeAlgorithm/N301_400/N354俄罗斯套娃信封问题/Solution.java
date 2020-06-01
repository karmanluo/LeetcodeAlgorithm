package LeetcodeAlgorithm.N301_400.N354俄罗斯套娃信封问题;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/20 18:40
 */
public class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]){
                    return b[1] - a[1];
                }else {
                    return a[0] - b[0];
                }
            }
        });
        int[] secNums = new int[envelopes.length];
        for (int i = 0; i < envelopes.length; i++) {
            secNums[i] = envelopes[i][1];
        }

        return lengthOfLIS(secNums);
    }

    private int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0)   return 0;

        int[] dp = new int[nums.length];
        int res = 0;

        for (int num : nums){
            int lo = 0, hi = res;
            while (lo < hi){
                int mid = lo + ((hi - lo) >>> 1);
                if (num > dp[mid]) lo = mid + 1;
                else    hi = mid;
            }
            dp[lo] = num;
            //res是下一个插入的点，也是上升子序列的个数
            if (lo == res)  res++;
        }

        return res;
    }
}
