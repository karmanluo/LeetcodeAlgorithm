package LeetcodeAlgorithm.N912数组排序;

//快速排序
public class Solution2 {
    public int[] sortArray(int[] nums) {

        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;

        int pivotIndex = partition(nums, start, end);
        quickSort(nums, start, pivotIndex - 1);
        quickSort(nums, pivotIndex + 1, end);
    }

    //将数组划分为两部分，都是有序的
    private int partition(int[] nums, int start, int end) {
        int pivotIndex = start + ((end - start) >>> 1);
        int pivotValue = nums[pivotIndex];

        swap(nums, pivotIndex, end);

        int finalIndex = start; //finalIndex equals to separateIndex
        for (int i = start; i < end; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, i, finalIndex);
                finalIndex++;
            }
        }

        swap(nums, finalIndex, end);
        return finalIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
