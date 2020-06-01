package LeetcodeAlgorithm.N101_200.N189旋转数组;

public class Solution {
    public void rotate(int[] nums, int k) {
        if (k % nums.length == 0) return;
        int r = k % nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, r - 1);
        reverse(nums, r, nums.length - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        for (int i = 0; i < (end - start + 1) >>> 1; i++) {
            int tmp = nums[start + i];
            nums[start + i] = nums[end - i];
            nums[end - i] = tmp;
        }
    }
}
