package LeetcodeAlgorithm.N301_400.N315计算右侧小于当前元素的个数;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    int[] count;

    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();

        count = new int[nums.length];
        int[] indexes = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        mergeSort(nums, indexes, 0, nums.length - 1);
        for (int i = 0; i < count.length; i++) {
            res.add(count[i]);
        }
        return res;
    }

    private void mergeSort(int[] nums, int[] indexes, int start, int end) {
        if (start >= end) return;

        int mid = start + ((end - start) >>> 1);
        mergeSort(nums, indexes, start, mid);
        mergeSort(nums, indexes, mid + 1, end);
        merge(nums, indexes, start, mid, end);
    }

    private void merge(int[] nums, int[] indexes, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int leftIndex = start;
        int rightIndex = mid + 1;
        int tmpIndex = 0;
        int rightLargeNumCount = 0;

        while (leftIndex <= mid && rightIndex <= end) {
            if (nums[indexes[leftIndex]] > nums[indexes[rightIndex]]) {
                tmp[tmpIndex++] = indexes[rightIndex++];
                rightLargeNumCount++;
            } else {
                tmp[tmpIndex++] = indexes[leftIndex];
                count[indexes[leftIndex]] += rightLargeNumCount;
                leftIndex++;
            }
        }

        while (leftIndex <= mid) {
            tmp[tmpIndex++] = indexes[leftIndex];
            count[indexes[leftIndex]] += rightLargeNumCount;
            leftIndex++;
        }

        while (rightIndex <= end) {
            tmp[tmpIndex++] = indexes[rightIndex++];
        }

        for (int i = 0; i < tmp.length; i++) {
            indexes[start + i] = tmp[i];
        }
    }
}