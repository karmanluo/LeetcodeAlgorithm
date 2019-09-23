package LeetcodeAlgorithm.N41_FirstMissingPositive;
//Given an unsorted integer array, find the smallest missing positive integer.
//
// Example 1:
//Input: [1,2,0]
//Output: 3
//
// Example 2:
//Input: [3,4,-1,1]
//Output: 2
//
//
// Example 3:
//Input: [7,8,9,11,12]
//Output: 1

// Note:
//
// Your algorithm should run in O(n) time and uses constant extra space.
// Related Topics Array
/*
* 此题限定了时间复杂度为o(n)， 空间复杂度o(1)
* */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums == null || nums.length == 0) return 1;
        //交换位置，i和 nums[i] - 1
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]){
                swap(nums, i, nums[i] - 1);
            }
        }
        //找第一个不是i-1的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
