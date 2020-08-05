package AcWing.基础算法.N801二进制中1的个数;

import java.util.Scanner;

/*
    给定一个长度为n的数列，请你求出数列中每个数的二进制表示中1的个数。

    输入格式
    第一行包含整数n。

    第二行包含n个整数，表示整个数列。

    输出格式
    共一行，包含n个整数，其中的第 i 个数表示数列中的第 i 个数的二进制表示中1的个数。

    数据范围
    1≤n≤100000,
    0≤数列中元素的值≤10^9
    输入样例：
    5
    1 2 3 4 5
    输出样例：
    1 1 2 1 2
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            System.out.print(getBitNum(arr[i]) + " ");
        }
    }

    //去掉最后一位1
    private static int getBitNum(int n) {
        int cnt = 0;
        while (n > 0) {
            n = n & (n - 1);
            cnt++;
        }

        return cnt;
    }
}
