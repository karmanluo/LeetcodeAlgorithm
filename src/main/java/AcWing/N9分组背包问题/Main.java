package AcWing.N9分组背包问题;

/*
    有 N 组物品和一个容量是 V 的背包。

    每组物品有若干个，同一组内的物品最多只能选一个。
    每件物品的体积是 vij，价值是 wij，其中 i 是组号，j 是组内编号。

    求解将哪些物品装入背包，可使物品总体积不超过背包容量，且总价值最大。

    输出最大价值。

    输入格式
    第一行有两个整数 N，V，用空格隔开，分别表示物品组数和背包容量。

    接下来有 N 组数据：

    每组数据第一行有一个整数 Si，表示第 i 个物品组的物品数量；
    每组数据接下来有 Si 行，每行有两个整数 vij,wij，用空格隔开，分别表示第 i 个物品组的第 j 个物品的体积和价值；
    输出格式
    输出一个整数，表示最大价值。

    数据范围
    0<N,V≤100
    0<Si≤100
    0<vij,wij≤100
    输入样例
    3 5
    2
    1 2
    2 4
    1
    3 4
    1
    4 5
    输出样例：
    8
 */

import java.util.Scanner;

/**
 *      for (int i = 0; i < n; i++) {   // 枚举分组
 *          for (int j = V; j >= v; j--) { // 枚举体积
 *              f[j] = max (f[j], f[j - v[0]] + w[0], f[j - v[1]] + w[1], ..., f[j - v[s - 1] + w[s - 1]]);
 *          }
 *      }
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();

        int[] dp = new int[V + 1];
        for (int i = 0; i < n; i++) {   //枚举物品
            int s = sc.nextInt();
            int[] v = new int[s];
            int[] w = new int[s];
            for (int k = 0; k < s; k++) {
                v[k] = sc.nextInt();
                w[k] = sc.nextInt();
            }


            for (int j = V; j >= 0; j--) {  //枚举体积
                for (int k = 0; k < s; k++) {
                    if (j >= v[k]) dp[j] = Math.max(dp[j], dp[j - v[k]] + w[k]);
                }
            }
        }

        System.out.println(dp[V]);
    }
}