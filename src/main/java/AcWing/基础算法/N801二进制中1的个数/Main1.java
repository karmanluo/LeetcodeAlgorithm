package AcWing.基础算法.N801二进制中1的个数;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int cnt = 0;
            arr[i] = sc.nextInt();
            while (arr[i] > 0) {
                arr[i] -= lowBit(arr[i]);
                cnt++;
            }
            System.out.print(cnt + " ");
        }
    }

    //获取到最后 1 位 1对应的值，比如 lowBit(6) = 2;
    private static int lowBit(int n) {
        return n & -n;
    }
}
