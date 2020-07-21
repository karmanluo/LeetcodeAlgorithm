package Lintcode.N532逆序对;

public class Solution {
    public long reversePairs(int[] A) {return mergeSort(A, 0, A.length - 1);}

    private long mergeSort(int[] A, int low, int high) {
        if (low >= high) return 0;

        int mid = low + ((high - low) >>> 1);
        int sum = 0;
        sum += mergeSort(A, low, mid);
        sum += mergeSort(A, mid + 1, high);
        sum += merge(A, low, mid, high);
        return sum;
    }

    private int merge(int[] A, int low, int mid, int high) {
        int[] tmp = new int[high - low + 1];
        int leftIndex = low;
        int rightIndex = mid + 1;
        int index = 0;
        int sum = 0;

        while (leftIndex <= mid && rightIndex <= high) {
            if (A[leftIndex] <= A[rightIndex]) {
                tmp[index++] = A[leftIndex++];
            } else {
                tmp[index++] = A[rightIndex++];
                sum += mid - leftIndex + 1;
            }
        }
        while (leftIndex <= mid) tmp[index++] = A[leftIndex++];
        while (rightIndex <= high) tmp[index++] = A[rightIndex++];

        for (int i = 0; i < tmp.length; i++) {
            A[low+i] = tmp[i];
        }
        return sum;
    }
}
