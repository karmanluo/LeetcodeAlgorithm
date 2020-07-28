package AcWing.N7混合背包问题;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
    有 N 种物品和一个容量是 V 的背包。

    物品一共有三类：

    第一类物品只能用1次（01背包）；
    第二类物品可以用无限次（完全背包）；
    第三类物品最多只能用 si 次（多重背包）；
    每种体积是 vi，价值是 wi。

    求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。
    输出最大价值。

    输入格式
    第一行两个整数，N，V，用空格隔开，分别表示物品种数和背包容积。

    接下来有 N 行，每行三个整数 vi,wi,si，用空格隔开，分别表示第 i 种物品的体积、价值和数量。

    si=−1 表示第 i 种物品只能用1次；
    si=0 表示第 i 种物品可以用无限次；
    si>0 表示第 i 种物品可以使用 si 次；
    输出格式
    输出一个整数，表示最大价值。

    数据范围
    0<N,V≤1000
    0<vi,wi≤1000
    −1≤si≤1000
    输入样例
    4 5
    1 2 -1
    2 4 1
    3 4 0
    4 5 2
    输出样例：
    8
 */
public class Main {
    static class Goods {
        int kind, v, w;
        public Goods(int kind, int v, int w) {
            this.kind = kind;
            this.v = v;
            this.w = w;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int V = sc.nextInt();

        List<Goods> goods = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            int v = sc.nextInt();
            int w = sc.nextInt();
            int s = sc.nextInt();
            if (s < 0) {
                //0 - 1 背包
                goods.add(new Goods(-1, v, w));
            } else if (s == 0) {
                // 完全背包
                goods.add(new Goods(0, v, w));
            } else {
                // 多重背包转 0-1 背包
                for (int k = 1; k <= s; k *= 2) {
                    s -= k;
                    goods.add(new Goods(-1, k * v, k * w));
                }
                goods.add(new Goods(-1, s * v, s * w));
            }
        }

        int[] dp = new int[V + 1];
        for (Goods good : goods) {
            if (good.kind == -1) {
                for (int j = V; j >= good.v; j--) {
                    dp[j] = Math.max(dp[j], dp[j - good.v] + good.w);
                }
            } else {
                for (int j = good.v; j <= V; j++) {
                    dp[j] = Math.max(dp[j], dp[j - good.v] + good.w);
                }
            }
        }

        System.out.println(dp[V]);
    }
}
