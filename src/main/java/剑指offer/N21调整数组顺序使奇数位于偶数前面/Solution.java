package 剑指offer.N21调整数组顺序使奇数位于偶数前面;

public class Solution {
    public int[] exchange(int[] nums) {
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 1){
                int tmp = nums[i];
                nums[i] = nums[start];
                nums[start] = tmp;
                start++;
            }
        }
        return nums;
    }
}
