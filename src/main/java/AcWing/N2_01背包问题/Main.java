package AcWing.N2_01背包问题;

import java.util.Scanner;

/**
 *  0-1背包 ： 一个物品只有 选 / 不选 的两种情况，每件物品只能选一次
 *  完全背包 ：每件物品可以选多次，只要背包容量是够的
 *  多重背包问题 ： 每个物品可以被选的次数上限是不同的
 *  混合背包问题 ： 物品有很多种
 *  二维费用的背包问题 ： 体积、重量 都有限制
 *  分组背包问题 ： 把物品分组， 每组内的物品只能选一件
 *  背包问题求方案数 ： 求方案、最小值、方案数
 *  求背包问题的方案 ： 方案内容
 *  有依赖的背包问题 ： 选一个问题之后必须选一件他所依赖的物品
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int V = sc.nextInt();

        int[] v = new int[N + 1];
        int[] w = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            v[i] = sc.nextInt();
            w[i] = sc.nextInt();
        }

        int[][] dp = new int[N + 1][V + 1];
        // 正式工作的代码
        /*
        定义一个二阶矩阵dp[N+1][V+1],
        这里之所以要N+1和V+1，是因为第0行表示只能选择第0个物品的时候，即没有物品的时候
        第0列表示背包的体积为0的时候，即不能装任何东西的时候

        dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值
        对于dp[i][j]有两种情况：
        1. 不选择当前的第i件物品/第i件物品比背包容量要大，则dp[i][j] = dp[i-1][j]
        2. 选择当前的第i件物品（潜在要求第i件物品体积小于等于背包总容量），则能装入的物品最大价值为：
            当前物品的价值 加上 背包剩余容量在只能选前i-1件物品的情况下的最大价值
            dp[i][j] = dp[i-1][j-v[i]] + w[i]
        dp[i][j]在两种情况中选择比较大的情况作为当前的最优解；
        即：
        if(j >= v[i]):
            dp[i][j] = max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])
        else:
            dp[i][j] = dp[i-1][j]
        */
        dp[0][0] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                if (j >= v[i]) dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                else dp[i][j] = dp[i - 1][j];
            }
        }

        System.out.println(dp[N][V]);
    }
}
