package LeetcodeAlgorithm.N101_200.N136只出现一次的数字;

public class Solution {
    public int singleNumber(int[] nums) {
        int r = 0;
        for (int i = 0; i < nums.length; i++) {
            r ^= nums[i];
        }
        return r;
    }
}
