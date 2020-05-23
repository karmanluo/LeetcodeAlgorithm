package LeetcodeAlgorithm.N0___100.N75_SortColors;

public class Solution2 {
    public void sortColors(int[] nums) {
        //one pass solution
        int start = 0, end = nums.length - 1;
        for (int index = 0; index <= end; index++) {
            if (nums[index] == 0){
                nums[index] = nums[start];
                nums[start] = 0;
                start++;
            }
            if(nums[index] == 2){
                nums[index] = nums[end];
                nums[end] = 2;
                end--;
                index--;
            }
        }
    }
}
