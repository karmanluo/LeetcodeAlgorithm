package LeetcodeAlgorithm.N0___100.N26_有序数组去重;

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null) return 0;
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == nums.length - 1) {
                nums[len++] = nums[i];
                break;
            }
            if (nums[i] != nums[i + 1])  nums[len++] = nums[i];
        }
        return len;
    }
}
