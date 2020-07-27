package AcWing.N4多重背包问题1;

import java.util.Scanner;

/**
 *          多重背包问题 ： 每个物品可以被选的次数上限是不同的
 */
/*
        有 N 种物品和一个容量是 V 的背包。

        第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。

        求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
        输出最大价值。

        输入格式
        第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。

        接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。

        输出格式
        输出一个整数，表示最大价值。

        数据范围
        0<N,V≤100
        0<vi,wi,si≤100
        输入样例
        4 5
        1 2 3
        2 4 1
        3 4 3
        4 5 2
        输出样例：
        10
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();

        int[] v = new int[n + 1];
        int[] w = new int[n + 1];
        int[] s = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
            s[i] = sc.nextInt();
        }
/**
 *      f[i]总体积是 i 的情况下，最大价值是多少
 *      for (int i = 0; i < n; i++) {
 *          for (int j = V; j >= v[i]; j--) {
 *              f[j] = max(f[j], f[j - v[i]] + w[i], f[j - 2 * v[i]] + 2 * w[i], ...)
 *          }
 *      }
 */     int[] dp = new int[V + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = V; j > 0; j--) {
                for (int k = 0; k <= s[i] && k * v[i] <= j; k++) {
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }

        System.out.println(dp[V]);
    }
}
