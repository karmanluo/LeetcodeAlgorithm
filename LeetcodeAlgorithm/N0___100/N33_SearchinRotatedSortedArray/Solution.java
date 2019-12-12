package LeetcodeAlgorithm.N33_SearchinRotatedSortedArray;
//Suppose an array sorted in ascending升序 order is rotated旋转 at some pivot(中心点) unknown to you beforehand（adv 事先）.
//
// (i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).
//
// You are given a target value to search. If found in the array return its index, otherwise return -1.
//找不到就返回-1，找到就返回index
// You may assume no duplicate（一样的） exists in the array.
// Your algorithm's runtime complexity must be in the order of O(log n).
//时间复杂度限制在o（log n）
// Example 1:
//Input: nums = [4,5,6,7,0,1,2], target = 0
//Output: 4
//
// Example 2:
//Input: nums = [4,5,6,7,0,1,2], target = 3
//Output: -1

// Related Topics Array Binary Search
//leetcode submit region begin(Prohibit modification and deletion)
/*
* 解题思路：其实就是一个查找target数
* 查找要想在log n 的时间复杂度，考虑使用二分查找
* Revised Binary Search
*   If the entire left part is monotonically increasing, which means the pivot point is on the right part
    If left <= target < mid ------> drop the right half
    Else ------> drop the left half
    If the entire right part is monotonically increasing, which means the pivot point is on the left part
    If mid < target <= right ------> drop the left half
    Else ------> drop the right half
* */
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return -1;
        }
        int left = 0, right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if (target == nums[mid]) return mid;
            if (nums[left] <= nums[mid]){//代表左半边的是递增的
                if(nums[left] <= target && target <= nums[mid]){//target在左半边，不要右半边
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }else {//代表右边是递增的
                if(nums[mid] <= target && target <= nums[right]){//target在右半边，不要左半边
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
