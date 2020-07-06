package LeetcodeAlgorithm.N101_200.N162寻找峰值;

public class Solution {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] < nums[mid + 1]) lo = mid + 1;
            else hi = mid;
        }

        return lo;
    }
}
