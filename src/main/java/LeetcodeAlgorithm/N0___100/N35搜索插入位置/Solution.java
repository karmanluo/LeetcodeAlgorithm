package LeetcodeAlgorithm.N0___100.N35搜索插入位置;

public class Solution {
    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int lo = 0, hi = len;
        while (lo < hi) {
            int mid = lo + (hi -lo) / 2;
            if (nums[mid] >= target) hi = mid;
            else lo = mid + 1;
        }

        return lo;
    }
}
