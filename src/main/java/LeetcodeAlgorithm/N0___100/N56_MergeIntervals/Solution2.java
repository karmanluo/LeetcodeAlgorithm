package LeetcodeAlgorithm.N0___100.N56_MergeIntervals;

import java.util.Arrays;

public class Solution2 {
    public int[][] merge(int[][] intervals) {
        int[][] res = new int[intervals.length][2];
        int index = -1;

        Arrays.sort(intervals, (a, b)->a[0] - b[0]);
        for (int[] interval : intervals) {
            if (index == -1 || interval[0] > res[index][1]) res[++index] = interval;
            else res[index][1] = Math.max(res[index][1], interval[1]);
        }

        return Arrays.copyOf(res, index + 1);
    }
}