package AcWing.基础算法.N786第k个数;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);
        int[] arr = new int[n];

        String[] res = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(res[i]);

        System.out.println(quickSort(arr, 0, n - 1, k));;
        br.close();
    }

    private static int quickSort(int[] arr, int l, int r, int k) {
        if (l == r) return arr[l];

        int x = arr[(l + r) >> 1], i = l - 1, j = r + 1;

        while (i < j) {
            do i++; while (arr[i] < x);
            do j--; while (arr[j] > x);
            if (i < j) swap(arr, i, j);
        }

        int sl = j - l + 1;
        if (k <= sl) return quickSort(arr, l, j, k);
        else return quickSort(arr, j + 1, r, k - sl);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
