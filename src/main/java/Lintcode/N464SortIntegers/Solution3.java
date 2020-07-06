package Lintcode.N464SortIntegers;

public class Solution3 {
    public void heapSort(int[] arr) {
        //将待排序的序列排成一个大顶堆
        buildHeap(arr, arr.length);

        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            heapify(arr, 0, i);
        }
    }

    private void buildHeap(int[] arr, int n){
        int lastIndex = n - 1;
        int parent = (lastIndex - 1) / 2;

        for (int i = parent; i >= 0; i--) {
            heapify(arr, i, n);
        }
    }

    private void heapify(int[] arr, int i, int n) {
        if (i >= n)  return;

        int max = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && arr[left] > arr[max]){
            max = left;
        }
        if (right < n && arr[right] > arr[max]){
            max = right;
        }
        if (max != i){
            swap(arr, i, max);
            heapify(arr, max, n);
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

}
