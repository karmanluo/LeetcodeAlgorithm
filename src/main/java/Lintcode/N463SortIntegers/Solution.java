package Lintcode.N463SortIntegers;

public class Solution {
    //冒泡排序
    public void sortIntegers(int[] A) {
        for (int i = 0; i < A.length - 1; i++) {
            for (int j = 0; j < A.length - i - i; j++) {
                if (A[j] > A[j + 1]){
                    int tmp = A[j];
                    A[j] = A[j+1];
                    A[j+1] = tmp;
                }
            }
        }
    }
}
