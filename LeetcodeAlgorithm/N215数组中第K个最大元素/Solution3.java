package LeetcodeAlgorithm.N215数组中第K个最大元素;

import java.util.Random;

/**
 * 利用快速排序的思想
 */
import java.util.Random;
class Solution3 {
    int [] nums;
    public int findKthLargest(int[] nums, int k) {
        this.nums = nums;
        int size = nums.length;
        // kth largest is indexed (N - k)th smallest
        return quickselect(0, size - 1, size - k);
    }

    public int quickselect(int left, int right, int destPos) {
        if (left == right)
            return this.nums[left];
        Random random_num = new Random();
        int pivot_index = left + random_num.nextInt(right - left);

        pivot_index = partition(left, right, pivot_index);

        // the pivot is on (N - k)th smallest position
        if (destPos == pivot_index)
            return this.nums[destPos];
            // go left side
        else if (destPos < pivot_index)
            return quickselect(left, pivot_index - 1, destPos);
        // go right side
        return quickselect(pivot_index + 1, right, destPos);
    }

    public void swap(int a, int b) {
        int tmp = this.nums[a];
        this.nums[a] = this.nums[b];
        this.nums[b] = tmp;
    }

    public int partition(int left, int right, int pivot_index) {
        int pivot = this.nums[pivot_index];
        // 1. move pivot to end
        swap(pivot_index, right);
        int store_index = left;
        // 2. move all smaller elements to the left
        for (int i = left; i <= right; i++) {
            if (this.nums[i] < pivot) {
                swap(store_index, i);
                store_index++;
            }
        }
        // 3. move pivot to its final place
        swap(store_index, right);

        return store_index;
    }
}


