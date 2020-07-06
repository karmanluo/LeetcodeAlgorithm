package LeetcodeAlgorithm.N601_700.N698划分位K个相等子集;

import java.util.Arrays;

public class Solution {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int n = nums.length;
        if (k > n) return false;
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % k != 0) return false;
        Arrays.sort(nums);
        int beginIndex = n - 1, target = sum / k;
        if (nums[beginIndex] > target) return false;
        while (beginIndex >= 0 && nums[beginIndex] == target) {
            k--;
            beginIndex--;
        }

        return partition(new int[k], nums, beginIndex, target);
    }

    private boolean partition(int[] subsets, int[] nums, int index, int target) {
        if (index < 0) return true;

        int selected = nums[index];
        for (int i = 0; i < subsets.length; i++) {
            if (selected + subsets[i] <= target) {
                subsets[i] += selected;
                if (partition(subsets, nums, index - 1, target)) return true;
                subsets[i] -= selected;
            }
        }
        return false;
    }
}
