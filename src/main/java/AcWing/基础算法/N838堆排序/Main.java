package AcWing.基础算法.N838堆排序;

import java.util.Scanner;

/*
    输入一个长度为n的整数数列，从小到大输出前m小的数。

    输入格式
    第一行包含整数n和m。

    第二行包含n个整数，表示整数数列。

    输出格式
    共一行，包含m个整数，表示整数数列中前m小的数。

    数据范围
    1≤m≤n≤10^5，
    1≤数列中元素≤10^9
    输入样例：
    5 3
    4 5 1 3 2
    输出样例：
    1 2 3
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int size = n;
        for (int i = (size - 1) / 2; i >= 0; i--) down(arr, i, size);

        while (m-- > 0) {
            System.out.print(arr[0] + " ");
            arr[0] = arr[size - 1];
            size--;
            down(arr, 0, size);
        }
    }

    private static void down(int[] arr, int i, int n) {
        int c1 = 2 * i + 1, c2 = 2 * i + 2;
        int min = i;
        if (c1 < n && arr[c1] < arr[min]) min = c1;
        if (c2 < n && arr[c2] < arr[min]) min = c2;

        if (i != min) {
            swap(arr, i, min);
            down(arr, min, n);
        }
    }

    public static void swap(int[]arr, int i, int j) {
        int t = arr[i]; arr[i] = arr[j]; arr[j] = t;
    }
}
