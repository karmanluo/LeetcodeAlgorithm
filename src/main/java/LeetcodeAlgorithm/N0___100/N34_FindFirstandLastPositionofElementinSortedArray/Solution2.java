package LeetcodeAlgorithm.N0___100.N34_FindFirstandLastPositionofElementinSortedArray;

public class Solution2 {

    public int[] searchRange(int[] nums, int target) {
        int[] targetRange = {-1, -1};

        int leftIndex = searchRangeBinarySearch(nums, target, true);
        if (leftIndex == nums.length || nums[leftIndex] != target) return targetRange;

        targetRange[0] = leftIndex;
        targetRange[1] = searchRangeBinarySearch(nums, target, false) - 1;
        return targetRange;
    }

    //该插入此数的第一个位置 true
    //该插入此数的最后一个位置 false
    public int searchRangeBinarySearch(int[] nums, int target, boolean flag) {
        int lo = 0, hi = nums.length;

        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (nums[mid] > target || (flag && nums[mid] == target)) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }

}
