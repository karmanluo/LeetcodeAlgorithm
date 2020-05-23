package Lintcode.N463SortIntegers;

public class Solution2 {
    //插入排序
    public void sortIntegers(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            int currNumber = nums[i];
            int j = i - 1;
            while (j >= 0 && nums[j] > currNumber) {
                nums[j + 1] = nums[j];
                j--;
            }
            nums[j + 1] = currNumber;
        }
    }
}
