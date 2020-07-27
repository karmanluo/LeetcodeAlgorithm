package AcWing.N5多重背包问题2;
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
    0<N≤1000
    0<V≤2000
    0<vi,wi,si≤2000
    提示：
    本题考查多重背包的二进制优化方法。

    输入样例
    4 5
    1 2 3
    2 4 1
    3 4 3
    4 5 2
    输出样例：
    10
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *      问题和上一道题目一样，现在数据范围更大，如何进行优化？  时间复杂度 o(n^3) 优化---->
 *
 *      用二进制进行优化
 *      s  - 1 - 2 - 4 - 8 - ...    log(s)
 *      1000 * 11 * 2000 ~= 2 * 10^7        C++ 1s大概可以一秒执行10^7 ~ 10^8 次操作
 */
public class Main {

    static class Good {
        int v, w;
        public Good(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    /**
     * 通过把一个物品转化为log(s)个物品，此问题就可以转化为 0-1 背包问题
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();

        List<Good> goods = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            int s = sc.nextInt();

            for (int k = 1; k <= s; k *= 2) {
                s -= k;
                goods.add(new Good(v * k, k * w));
            }
            if (s > 0) goods.add(new Good(s * v, s * w));
        }

        int[] dp = new int[V + 1];
        for (Good good : goods) {
            for (int j = V; j >= good.v; j--) {
                dp[j] = Math.max(dp[j], dp[j - good.v] + good.w);
            }
        }

        System.out.println(dp[V]);
    }
}
