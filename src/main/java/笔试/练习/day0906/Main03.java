package 笔试.练习.day0906;

import java.util.Scanner;

public class Main03 {
    static int n, m;
    static double[][][] arr;
    static int[][] vis;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        arr = new double[n][m][3];
        vis = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[i][j][0] = sc.nextDouble();
                arr[i][j][1] = sc.nextDouble();
                arr[i][j][2] = sc.nextDouble();
            }
        }

        vis[0][0] = 0;
        System.out.println(doSome());
    }

    private static int doSome() {
        int[] dx = {-1, 0}, dy = {0, -1}; //先上 再左
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < m; y++) {
                for (int k = 0; k < 2; k++) {
                    int a = x + dx[k];
                    int b = y + dy[k];
                    if (a >= 0 && a < n && b >= 0 && b < m) {
                        if (a != 0 && b != 0 && vis[a][b] == 0) continue;
                        vis[x][y] += (vis[a][b] + 1 + (1.0 / Math.pow(1 - arr[a][b][2], 2))
                        * arr[a][b][2]) * arr[a][b][k];
                    }
                }
            }
        }

        return vis[n - 1][m - 1];
    }
}
