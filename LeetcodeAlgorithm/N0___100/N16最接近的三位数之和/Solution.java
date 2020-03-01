package LeetcodeAlgorithm.N0___100.N16最接近的三位数之和;

import java.util.Arrays;

public class Solution {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3 || nums == null) return 0;

        //res初始值不能随意设置
        int res = nums[0] + nums[1] + nums[nums.length - 1];
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i == 0 || (i > 0 && nums[i - 1] != nums[i])){
                int lo = i + 1, hi = nums.length - 1;
                while (lo < hi){
                    int sum = nums[i] + nums[lo] + nums[hi];
                    if (sum > target){
                        hi--;
                    }else if (sum == target) return sum;
                    else lo++;

                    if (Math.abs(target - sum) < Math.abs(target - res)){
                        res = sum;
                    }
                }
            }
        }
        return res;
    }
}
