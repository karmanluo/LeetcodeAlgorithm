package AcWing.基础算法.搜索与图论.N844走迷宫;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    给定一个n*m的二维整数数组，用来表示一个迷宫，数组中只包含0或1，其中0表示可以走的路，1表示不可通过的墙壁。

    最初，有一个人位于左上角(1, 1)处，已知该人每次可以向上、下、左、右任意一个方向移动一个位置。

    请问，该人从左上角移动至右下角(n, m)处，至少需要移动多少次。

    数据保证(1, 1)处和(n, m)处的数字为0，且一定至少存在一条通路。

    输入格式
    第一行包含两个整数n和m。

    接下来n行，每行包含m个整数（0或1），表示完整的二维数组迷宫。

    输出格式
    输出一个整数，表示从左上角移动至右下角的最少移动次数。

    数据范围
    1≤n,m≤100
    输入样例：
    5 5
    0 1 0 0 0
    0 1 0 1 0
    0 0 0 0 0
    0 1 1 1 0
    0 0 0 1 0
    输出样例：
    8
 */
public class Main {
    static int n, m;
    static int[][] g, d;
    static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static class Pair {
        int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        g = new int[n][m];
        d = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                g[i][j] = sc.nextInt();
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Pair> q = new LinkedList<>();

        q.offer(new Pair(0, 0));
        while (!q.isEmpty()) {
            Pair pair = q.poll();

            for (int[] dir : dirs) {
                int x = pair.x + dir[0];
                int y = pair.y + dir[1];
                if (x < 0 || y < 0 || x >= n || y >= m || g[x][y] == 1 || d[x][y] != 0) continue;
                d[x][y] = d[pair.x][pair.y] + 1;
                q.offer(new Pair(x, y));
            }
        }

        return d[n - 1][m - 1];
    }
}
