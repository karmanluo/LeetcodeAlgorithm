package AcWing.基础算法.N789数的范围;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int len = sc.nextInt();
        int n = sc.nextInt();
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) arr[i] = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int t = sc.nextInt();
            System.out.println(findFirstPos(arr, 0, len - 1, t) + " " + findLastPos(arr, 0, len - 1, t));
        }
    }

    private static int findLastPos(int[] arr, int l, int r, int t) {
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (arr[mid] <= t) l = mid;
            else r = mid - 1;
        }

        return arr[l] == t ? l : -1;
    }

    private static int findFirstPos(int[] arr, int l, int r, int t) {
        while (l < r) {
            int mid = l + (r - l >> 1);
            if (arr[mid] >= t) r = mid;
            else l = mid + 1;
        }

        return arr[l] == t ? l : -1;
    }
}
