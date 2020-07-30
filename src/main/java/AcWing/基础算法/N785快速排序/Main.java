package AcWing.基础算法.N785快速排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] res = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(res[i]);

        quickSort(arr, 0, n - 1);

        for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
        br.close();
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;

        int x = arr[(l + r) >> 1], i = l - 1, j = r + 1;

        while (i < j) {
            do i++; while (arr[i] < x);
            do j--; while (arr[j] > x);
            if (i < j) swap(arr, i, j);
        }
        quickSort(arr, l, j);
        quickSort(arr, j + 1, r);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
