package LeetcodeAlgorithm.N401_500.N416分割等和子集;

public class Solution3 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 != 0) return false;
        return helper(nums, 0, sum / 2);
    }

    public boolean helper(int[] nums, int i, int target) {
        if (i == nums.length || target < 0) return false;
        if (target == 0) return true;
        if (helper(nums, i + 1, target - nums[i])) return true;
        int j = i + 1;
        while (j < nums.length && nums[i] == nums[j]) {
            j++;
        }
        return helper(nums, j, target);
    }
}