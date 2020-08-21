package AcWing.基础算法.搜索与图论.N843n皇后问题;

import java.util.Scanner;

public class Main2 {
    private static char g[][];
    private static int n;
    private static boolean[] col, row, dg, udg;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        g = new char[n][n];
        col = new boolean[n]; row = new boolean[n];
        dg = new boolean[2 * n]; udg = new boolean[2 * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = '.';
            }
        }

        dfs(0, 0, 0);
    }

    private static void dfs(int i, int j, int size) {
        if (j == n) {j = 0; i++;}
        if (i == n ) {
            if (size == n) {
                for (int k = 0; k < n; k++) System.out.println(g[k]);
                System.out.println();
            }
            return;
        }

        //不放皇后
        dfs(i, j + 1, size);
        //放皇后
        if (!row[i] && !col[j] && !dg[i + j] && !udg[i - j + n]) {
            g[i][j] = 'Q';
            row[i] = col[j] = dg[i + j] = udg[i - j + n] = true;
            dfs(i, j + 1, size + 1);
            row[i] = col[j] = dg[i + j] = udg[i - j + n] = false;
            g[i][j] = '.';
        }
    }
}
