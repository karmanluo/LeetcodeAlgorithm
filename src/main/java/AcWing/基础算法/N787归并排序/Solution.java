package AcWing.基础算法.N787归并排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);

        mergeSort(arr, 0, n - 1);

        for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
    }

    private static void mergeSort(int[] arr, int l, int r) {
        if (l == r) return;

        int m = l + ((r - l) >> 1);
        mergeSort(arr, l, m);
        mergeSort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    private static void merge(int[] arr, int l, int m, int r) {
        int n = r - l + 1;
        int[] temp = new int[n];
        int i = l, j = m + 1, k = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (int x = 0; x < n; x++) arr[l + x] = temp[x];
    }
}
