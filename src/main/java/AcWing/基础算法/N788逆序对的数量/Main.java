package AcWing.基础算法.N788逆序对的数量;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        System.out.println(mergerSort(arr, 0, n - 1));;
    }

    private static long mergerSort(int[] arr, int l, int r) {
        if (l == r) return 0;

        int m = l + ((r - l) >> 1);
        long lSum = mergerSort(arr, l, m);
        long rSum = mergerSort(arr, m + 1, r);

        return lSum + rSum + merge(arr, l, m, r);
    }

    private static long merge(int[] arr, int l, int m, int r) {
        int n = r - l + 1;
        int[] temp =  new int[n];
        int i = l, j = m + 1, k = 0;
        long cnt = 0;

        while (i <= m && j <= r) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else {
                cnt += m - i + 1;
                temp[k++] = arr[j++];
            }
        }
        while (i <= m) temp[k++] = arr[i++];
        while (j <= r) temp[k++] = arr[j++];

        for (int p = 0; p < n; p++) arr[l + p] = temp[p];

        return cnt;
    }
}
