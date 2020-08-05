package AcWing.基础算法.N799最长连续不重复子序列;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
/*
    给定一个长度为n的整数序列，请找出最长的不包含重复数字的连续区间，输出它的长度。

    输入格式
    第一行包含整数n。

    第二行包含n个整数（均在0~100000范围内），表示整数序列。

    输出格式
    共一行，包含一个整数，表示最长的不包含重复数字的连续子序列的长度。

    数据范围
    1≤n≤100000
    输入样例：
    5
    1 2 2 3 5
    输出样例：
    3
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr= new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int res = 1;
        Set<Integer> set = new HashSet<>();
        for (int r = 0, l = 0; r < n; r++) {
            while (set.contains(arr[r])) set.remove(arr[l++]);
            set.add(arr[r]);
            res = Math.max(res, r - l + 1);
        }

        System.out.println(res);
    }
}
