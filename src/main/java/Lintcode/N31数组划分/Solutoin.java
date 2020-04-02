package Lintcode.N31数组划分;

public class Solutoin {
    public int partitionArray(int[] nums, int k) {
        if (nums.length == 0) return 0;

        int finalIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k){
                swap(nums, i, finalIndex);
                finalIndex++;
            }
        }
        return finalIndex;
    }

    private void swap(int[] nums, int i, int finalIndex) {
            int tmp = nums[i];
            nums[i] = nums[finalIndex];
            nums[finalIndex] = tmp;
    }
}
