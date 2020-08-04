package AcWing.基础算法.N797差分;

import java.util.Scanner;

/*
    输入一个长度为n的整数序列。

    接下来输入m个操作，每个操作包含三个整数l, r, c，表示将序列中[l, r]之间的每个数加上c。
    请你输出进行完所有操作后的序列。

    输入格式
    第一行包含两个整数n和m。
    第二行包含n个整数，表示整数序列。
    接下来m行，每行包含三个整数l，r，c，表示一个操作。

    输出格式
    共一行，包含n个整数，表示最终序列。

    数据范围
    1≤n,m≤100000,
    1≤l≤r≤n,
    −1000≤c≤1000,
    −1000≤整数序列中元素的值≤1000
    输入样例：
    6 3
    1 2 2 1 2 1
    1 3 1
    3 5 1
    1 6 1
    输出样例：
    3 4 5 3 4 2
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(), m = sc.nextInt();
        int[] a = new int[n + 1];
        int[] b = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = sc.nextInt();

        /**
         *  思想 ： a[i] = b[1] + b[2] + ... + b[i]
         *          只操作 b 可以影响最后 a 的结果
         */
        for (int i = 1; i <= n; i++) insert(b, i, i, a[i]);

        while (m-- > 0) {
            int l = sc.nextInt(), r = sc.nextInt(), c = sc.nextInt();
            insert(b, l, r, c);
        }

        for (int i = 1; i <= n; i++) {
            a[i] = a[i - 1] + b[i];
            System.out.print(a[i] + " ");
        }
    }

    public static void insert(int[] b, int l, int r, int c) {
        b[l] += c;
        if (r == b.length - 1) return;
        b[r + 1] -= c;
    }

}
