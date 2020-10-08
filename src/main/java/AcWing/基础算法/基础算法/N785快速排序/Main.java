package AcWing.基础算法.基础算法.N785快速排序;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        // l， r 是为了方便每次统一操作
        int x = arr[l + r >> 1], i = l - 1, j = r + 1;

        while (i < j) {
            do i++; while(arr[i] < x);
            do j--; while(arr[j] > x);
            if (i < j) swap(arr, i, j);
        }

        // 执行完一遍 i 左边都是 <= x; j 右边都是 >= x, 所以在下面也可以用quickSort(arr, l, i - 1);
        //quickSort(arr, i, r); 但是这样写的时候注意边界情况
        // 用 j 的时候，x不能取arr[r] = eg:[1, 2] 进入死循环
        // 用 i 的时候, x不能取arr[l] = eg:[1, 2] 进入死循环
        quickSort(arr, l, j);       //这种写法对应着，l + r >> 1;否则,该用 l + r + 1 >> 1
        quickSort(arr, j + 1, r);
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i]; arr[i] = arr[j]; arr[j] = temp;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) arr[i] = Integer.parseInt(s[i]);

        quickSort(arr, 0, n - 1);

        for (int i = 0; i < n; i++) System.out.print(arr[i] + " ");
    }
}