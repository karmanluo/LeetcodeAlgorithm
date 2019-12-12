package LeetcodeAlgorithm.N31_NextPermutation;

//Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
//
// If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
//
// The replacement must be in-place and use only constant extra memory.
//
// Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
//
// 1,2,3 → 1,3,2
//3,2,1 → 1,2,3
//1,1,5 → 1,5,1
// Related Topics Array

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums.length == 0 || nums == null) return;
        int n = nums.length;
        int j = n - 2, i = n - 1;
        while (j >= 0 && nums[j] >= nums[j + 1]) {//从右往左找到第一个不是升序得元素
            j--;
        }
        if (j >= 0) {
            while (nums[i] <= nums[j] && i > j) i--;
            swap(nums, i, j);
        }
        reverse(nums, j+1, n-1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public void reverse(int[] nums, int i,int j){
        while(i<j)
            swap(nums, i++, j--);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

