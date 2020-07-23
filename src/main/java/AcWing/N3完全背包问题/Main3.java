package AcWing.N3完全背包问题;

import java.util.Scanner;

public class Main3 {
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
        sc.close();

        /**
         *  优化空间之后的写法
         *
         *  for (int i = 1; i <= N; i++) {
         *      for (int j = 1; j <= V; j++) {
         *          dp[j] = dp[j]; //等价于  dp[i][j] = dp[i - 1][j];
         *          if (j >= v[i]) dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
         *          // 等价于 dp[i][j] = Math.max(dp[i][j], dp[i][j - v[i]] + w[i]);
         *       }
         *  }
         */
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = v[i]; j <= V; j++) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }

        System.out.println(dp[V]);
    }
}
