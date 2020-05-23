package LeetcodeAlgorithm.N215数组中第K个最大元素;

import java.util.Random;

/**
 * 利用快速排序的思想
 */
import java.util.Random;
class Solution3 {
    public int findKthLargest(int[] nums, int k) {
        int size = nums.length;
        // kth largest is indexed (N - k)th smallest
        return quickselect(nums ,0, size - 1, size - k);
    }

    public int quickselect(int[] nums, int left, int right, int destPos) {
        if (left == right)
            return nums[left];
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(nums, left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (destPos == pivot_index)
            return nums[destPos];
            // go left side
        else if (destPos < pivot_index)
            return quickselect(nums, left, pivot_index - 1, destPos);
        // go right side
        return quickselect(nums, pivot_index + 1, right, destPos);
    }

    public void swap(int[] nums,int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    public int partition(int[] nums, int left, int right, int pivot_index) {
        int pivot = nums[pivot_index];
        // 1. move pivot to end
        swap(nums ,pivot_index, right);
        int store_index = left;
        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (nums[i] < pivot) {
                swap(nums,store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(nums ,store_index, right);

        return store_index;
    }
}


