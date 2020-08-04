package AcWing.基础算法.N795前缀和;

import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) arr[i] = sc.nextInt();

        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) s[i] = s[i - 1] + arr[i];

        while (m-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            System.out.println(s[r] - s[l - 1]);
        }
    }
}
