package Lintcode.N463SortIntegers;

public class Solution3 {
    //选择排序
    public void sortIntegers(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int tmp = nums[i];
            int flag = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] < tmp) {
                    tmp = nums[j];
                    flag = j;
                }
            }
            nums[flag] = nums[i];
            nums[i] = tmp;
        }
    }
}
