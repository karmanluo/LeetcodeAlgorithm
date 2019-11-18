package 面试.全国编程比赛;

import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        n = sc.nextInt();
        int a[] = new int[n];
        boolean b[] = new boolean[n];
        for (int x = 0; x < n; x++) {
            a[x] = sc.nextInt();
            b[x] = isPrime(a[x]);
        }
        int t = 0;
        for (int x = 2; x < n; x++) {
            if (b[x] && b[x - 1] && b[x - 2])
                t++;
        }
        System.out.println(t);
    }

    public static boolean isPrime(int x) {
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0)
                return false;
        }
        return true;
    }
}
