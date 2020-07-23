package AcWing.N3完全背包问题;

import java.util.Scanner;

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
        sc.close();

        int[][] dp = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                for (int k = 0; k * v[i] <= j; k++) {
                    dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i - 1][j - k * v[i]] + k * w[i]));
                }
            }
        }

        System.out.println(dp[N][V]);
    }

}
