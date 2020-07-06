package LeetcodeAlgorithm.N912数组排序;

//堆排序
public class Solution {
    public int[] sortArray(int[] nums) {
        int len = nums.length;
        //调整为大顶堆
        int start = (len - 1) / 2;
        for (int i = start; i >= 0; i--) {
            heapify(nums, len, i);
        }

        //取最大放最后，重新调整
        for (int i = len - 1; i >= 0; i--) {
            swap(nums, 0, i);
            heapify(nums, i, 0);
        }

        return nums;
    }

    //n 数组长度  i index为i
    public void heapify(int[] nums, int n, int i) {
        int c1 = 2 * i + 1, c2 = 2 * i + 2;
        int max = i;
        if (c1 < n && nums[c1] > nums[max]) max = c1;
        if (c2 < n && nums[c2] > nums[max]) max = c2;
        if (max != i) {
            swap(nums, i, max);
            heapify(nums, n, max);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
