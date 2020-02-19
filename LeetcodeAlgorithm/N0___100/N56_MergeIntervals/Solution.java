package LeetcodeAlgorithm.N0___100.N56_MergeIntervals;
//Given a collection of intervals间隔, merge all overlapping intervals.
//
// Example 1:
//Input: [[1,3],[2,6],[8,10],[15,18]]
//Output: [[1,6],[8,10],[15,18]]
//Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
//
// Example 2:
//Input: [[1,4],[4,5]]
//Output: [[1,5]]
//Explanation: Intervals [1,4] and [4,5] are considered overlapping.
//
// NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.
// Related Topics Array Sort
/*
* 思路：先对二维数组中，按照每一行的第一个数进行排序，然后进行比较
* */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)  return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        List<int[]> res = new ArrayList<>();
        int[] newInterval = intervals[0];
        res.add(newInterval);
        for (int[] interval :intervals) {
            if (interval[0] <= newInterval[1]){
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
            else{
                newInterval = interval;
                res.add(newInterval);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}

