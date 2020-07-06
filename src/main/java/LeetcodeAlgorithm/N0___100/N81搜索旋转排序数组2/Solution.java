package LeetcodeAlgorithm.N0___100.N81搜索旋转排序数组2;

public class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;

        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);

            if(nums[mid] > nums[hi]) { //左边有序
                if (target >= nums[lo] && target <= nums[mid]) hi = mid;
                else lo = mid + 1;
            } else if (nums[mid] < nums[hi]) { //右边有序
                if (target > nums[mid] && target <= nums[hi]) lo = mid + 1;
                else hi = mid;
            } else  hi--;
        }

        return nums[lo] == target;
    }
}
