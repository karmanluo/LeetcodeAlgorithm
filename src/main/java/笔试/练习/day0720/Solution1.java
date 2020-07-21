package 笔试.练习.day0720;

import java.util.Scanner;

/**
 * 作者：tuogy
 * 链接：https://www.nowcoder.com/discuss/455801
 * 来源：牛客网
 *
 * 1. 给定N和K，求互不相同的正整数x,y,z使得x+y+z=N，且gcd(x,y)=gcd(x,z)=gcd(y,z)=K。
 * 条件：1 ≤N, K≤ 1e18
 * 思路：等式两边除K，得到x'+y'+z'=N'=N/K，且x',y',z'两两互素。
 * 当N'为偶数，直接构造x'=1, y'=N'/2, z' = y'-1满足条件。
 * 当N'为奇数，令x'=1,则y'+z'=N'-1。由于N'-1为偶数且y'和z'互素，必然有y',z'都为奇数。令y'=3,5,..., N' / 2逐个搜索即可。
 */

public class Solution1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[] target = new long[n];
        long[] factor = new long[n];
        for (int i = 0; i < n; i++) {
            target[i] = sc.nextLong();
            factor[i] = sc.nextLong();
        }


        for (int i = 0; i < n; i++) {
            helper(target[i], factor[i]);
        }
    }

    public static void helper(long target, long factor) {
        if (target % factor != 0 || target / factor < 6) {
            System.out.println(-1);
        } else {
            Long n = target / factor;
            if (n % 2 == 0) {
                long j = (n - 1) / 2;
                System.out.println(factor + " " + factor * j + " " + factor * (j + 1));
            } else {
                boolean found = false;
                for (long p = 3; p < n - 1 - p && found == false; p += 2) {
                    long q = n - 1 - p;
                    if (gcd(p, q) == 1) {
                        System.out.println(factor + " " + factor * p + " " + factor * q);
                        found = true;
                    }
                }
                if (!found) System.out.println(-1);
            }
        }
    }

    private static long gcd(long p, long q) {
        while (q != 0) {
            long temp = q;
            q = p % q;
            p = temp;
        }
        return p;
    }

}