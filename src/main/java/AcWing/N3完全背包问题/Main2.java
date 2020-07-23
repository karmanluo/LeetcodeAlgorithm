package AcWing.N3完全背包问题;

import java.util.Scanner;

public class Main2 {
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
         * 根据公式递推 可以得到一个
         *
         * f[i , j ] = max( f[i-1,j] , f[i-1,j-v]+w ,  f[i-1,j-2*v]+2*w , f[i-1,j-3*v]+3*w , .....)
         * f[i , j-v]= max(            f[i-1,j-v]   ,  f[i-1,j-2*v] + w , f[i-1,j-2*v]+2*w , .....)
         * 由上两式，可得出如下递推关系：
         *                         f[i][j]=max(f[i,j-v]+w , f[i-1][j])
         */
        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= v[i]) dp[i][j] = Math.max(dp[i][j], dp[i][j - v[i]] + w[i]);
            }
        }

        System.out.println(dp[N][V]);
    }
}
