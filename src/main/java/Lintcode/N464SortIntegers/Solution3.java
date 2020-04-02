package Lintcode.N464SortIntegers;

public class Solution3 {
    //堆排序
    //https://blog.csdn.net/zdp072/article/details/44227317
    public void heapSort(int[] arr) {
        //将待排序的序列排成一个大顶堆
        for (int i = arr.length / 2 - 1; i >= 0 ; i--) {
            heapAdjust(arr, i, arr.length);//调整的节点i以后的树的变化
        }
        // 逐步将每个最大值的根节点与末尾元素交换，并且再调整二叉树，使其成为大顶堆
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, i, 0); // 将堆顶记录和当前未经排序子序列的最后一个记录交换
            heapAdjust(arr, 0, i);
        }
    }

    private void swap(int[] arr, int index1, int index2) {
        int tmp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = tmp;
    }

    private void heapAdjust(int[] arr, int i, int n) {
        int father;
        int childIndex;
        for (father = arr[i]; leftChild(i) < n; i = childIndex){
            childIndex = leftChild(i);
            if (childIndex != n - 1 && arr[childIndex] < arr[childIndex + 1]){
                childIndex++;
            }
            if (arr[childIndex] > arr[i]){
                arr[i] = arr[childIndex];
            }else {
                break;
            }
            arr[childIndex] = father;
        }
    }

    private int leftChild(int i) {
        return 2 * i + 1;
    }
}
