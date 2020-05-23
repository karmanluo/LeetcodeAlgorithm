package Lintcode.N144交错正负数;

public class Solution {
    /**
     * 解题思路：
     * 首先将正数移到左边，左边正数多则和负数交换（数据reverse一下）
     * 然后将指针后移即可
     */
    public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length < 3) return;

        int n = A.length;
        int countPositive = 0;
        int posIndex = 0;

        int left = 1;
        int right = 0;
        //将正数移到左边
        for (int i = 0; i < A.length; i++) {
            if (A[i] > 0) {
                swap(A, i, posIndex);
                posIndex++;
                countPositive++;
            }
        }
        //reverse一下
        if (countPositive > n / 2) {
            left = 0;
            right = 1;
            int leftIndex = 0;
            int rightIndex = A.length - 1;
            while (leftIndex < rightIndex) {
                swap(A, leftIndex, rightIndex);
                leftIndex++;
                rightIndex--;
            }
        }

        while (left < n && right < n) {
            while (left < n && A[left] > 0) left += 2;
            while (right < n && A[right] < 0) right += 2;
            if (left >= n || right >= n) break;
            swap(A, left, right);
        }
    }

    private void swap(int[] a, int i, int posIndex) {
        int tmp = a[i];
        a[i] = a[posIndex];
        a[posIndex] = tmp;
    }
}
