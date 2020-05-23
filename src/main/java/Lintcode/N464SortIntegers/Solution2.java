package Lintcode.N464SortIntegers;

public class Solution2 {
    public void sortIntegers2(int[] A) {
        quickSort(A, 0, A.length - 1);
    }

    private void quickSort(int[] A, int start, int end) {
        if (start > end) return;

        int pivotIndex = partition(A, start, end);
        quickSort(A, start, pivotIndex - 1);
        quickSort(A, pivotIndex + 1, end);
    }

    private int partition(int[] A, int start, int end) {
        int pivotIndex = start + ((end - start) >>> 1);
        int pivot = A[pivotIndex];//其他位置也可以

        //将pivot移到最后
        swap(A, pivotIndex, end);
        //用于标记最后改交换到的位置
        int finalIndex = start;
        //将小于的元素放到左边
        for (int i = start; i < end; i++) {
            if (A[i] < pivot) {
                swap(A, i, finalIndex);
                finalIndex++;
            }
        }
        //最后交换到最终位置
        swap(A, finalIndex, end);
        return finalIndex;
    }

    public void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }
}
