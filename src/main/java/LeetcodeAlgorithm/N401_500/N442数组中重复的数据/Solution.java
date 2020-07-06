package LeetcodeAlgorithm.N401_500.N442数组中重复的数据;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null) return null;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0){
                nums[index] = - nums[index];
            }else{
                res.add(Math.abs(nums[i]));
            }
        }
        return res;
    }
}
