package AcWing.基础算法.搜索与图论.N849Dijkstra求最短路;

import java.util.Arrays;
import java.util.Scanner;

/*
    给定一个n个点m条边的 有向图 ，图中可能存在重边和自环，所有边权均为正值。

    请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。

    输入格式
    第一行包含整数n和m。

    接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

    输出格式
    输出一个整数，表示1号点到n号点的最短距离。

    如果路径不存在，则输出-1。

    数据范围
    1≤n≤500,
    1≤m≤10^5,
    图中涉及边长均不超过10000。

    输入样例：
    3 3
    1 2 2
    2 3 1
    1 3 4
    输出样例：
    3
 */
public class Main {
    //从这里看，边是比较多的，所有可以用邻接矩阵存数据

    static int N = 510;
    static int m, n;
    static int[][] g = new int[N][N];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];

    //注意：有向图和无向图相比，无向图可以看出有向图
    //如果有重边，保留距离最短的那条边
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();

        for (int i = 1; i <= n; i++) Arrays.fill(g[i], 0x3f3f); //权值不超过10000
        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            g[a][b] = Math.min(g[a][b], c);
        }

        // 表示1号点到n号点的最短距离
        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        Arrays.fill(dist, 0x3f3f);

        dist[1] = 0; //把自己的距离设置为 0

        //遍历一遍 找到一个最小的点，加入到集合中，这里加入到集合里，是通过st来做

        //迭代n次，n次迭代后获得最终结果集
        for (int i = 0; i < n; i++) {
            int t = -1; //表示还没有最短的点
            for (int j = 1; j <= n; j++) {
                if (!st[j] && (t == -1 || dist[t] > dist[j])) {
                    t = j;
                }
            } //循环后找到了最短的点，为 t

            st[t] = true; // t 加进结果集中

            //最后拿 t 更新一下结果集
            for (int j = 1; j <= n; j++) dist[j] = Math.min(dist[j], dist[t] + g[t][j]);
        }

        if (dist[n] == 0x3f3f) return -1;
        else return dist[n];
    }
}