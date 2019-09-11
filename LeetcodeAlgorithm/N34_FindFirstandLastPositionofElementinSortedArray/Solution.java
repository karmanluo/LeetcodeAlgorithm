package LeetcodeAlgorithm.N34_FindFirstandLastPositionofElementinSortedArray;

//Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target
// value.
// Your algorithm's runtime complexity must be in the order of O(log n).
//
// If the target is not found in the array, return [-1, -1].
//
// Example 1:
//Input: nums = [5,7,7,8,8,10], target = 8
//Output: [3,4]
//
// Example 2:
//Input: nums = [5,7,7,8,8,10], target = 6
//Output: [-1,-1]
// Related Topics Array Binary Search
/*
* 解题思路：我们需要找的是开始和结束位置的index
* 记住：二分查找可以找到目标数的第一个index或者最后一个index
* */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return new int[]{-1, -1};
        }
        int[] res = new int[2];
        res[0] = firstIndex(nums, target);
        res[1] = lastIndex(nums, target);
        return res;
    }
    private int firstIndex(int[] nums, int target){
        int index = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) >> 1;
            if (target == nums[mid]) index = mid;
            if (nums[mid] >= target){
                end = mid - 1;
            }else {
                start = mid + 1;
            }
        }
        return index;
    }
    private int lastIndex(int[] nums, int target){
        int index = -1;
        int start = 0, end = nums.length - 1;
        while (start <= end){
            int mid = (start + end) >> 1;
            if (target == nums[mid]) index = mid;
            if (nums[mid] <= target){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
        return index;
    }
}

