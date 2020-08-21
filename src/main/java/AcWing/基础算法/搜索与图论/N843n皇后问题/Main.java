package AcWing.基础算法.搜索与图论.N843n皇后问题;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        char[][] g = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = '.';
            }
        }

        boolean[] col = new boolean[n], dg = new boolean[2 * n], udg = new boolean[2 * n];
        dfs(g, col, dg, udg, n, 0);
    }

    private static void dfs(char[][] g, boolean[] col, boolean[] dg, boolean[] udg, int n, int size) {
        if (size == n) {
            for (int i = 0; i < n; i++) System.out.println(g[i]);
            System.out.println();
            return;
        }

        for (int j = 0; j < n; j++) {
            //第size列没有 Q ，正对角线没有 Q, 反对角线没有Q
            if (!col[j] && !dg[size + j] && !udg[j - size + n]) {
                g[size][j] = 'Q';
                col[j] = dg[size + j] = udg[j - size + n] = true;
                dfs(g, col, dg, udg, n, size + 1);
                col[j] = dg[size + j] = udg[j - size + n] = false;
                g[size][j] = '.';
            }
        }

    }
}