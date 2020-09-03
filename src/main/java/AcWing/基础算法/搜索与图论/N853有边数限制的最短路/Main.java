package AcWing.基础算法.搜索与图论.N853有边数限制的最短路;

import java.util.Arrays;
import java.util.Scanner;

/*
    给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
    请你求出从1号点到n号点的最多经过k条边的最短距离，如果无法从1号点走到n号点，输出impossible。

    注意：图中可能 存在负权回路 。

    输入格式
    第一行包含三个整数n，m，k。
    接下来m行，每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

    输出格式
    输出一个整数，表示从1号点到n号点的最多经过k条边的最短距离。
    如果不存在满足条件的路径，则输出“impossible”。

    数据范围
    1≤n,k≤500,
    1≤m≤10000,
    任意边长的绝对值不超过10000。

    输入样例：
    3 3 1
    1 2 1
    2 3 1
    1 3 3
    输出样例：
    3
 */
public class Main {
    static class Edge {
        int a, b, w;
        public Edge(int a, int b, int w){
            this.a = a; this.b = b; this.w = w;
        }
    }

    static int N = 10010, max = 0x3f3f3f3f;
    static int m, n, k;
    static int[] dist = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt(); k = sc.nextInt();

        Edge[] edge = new Edge[N];
        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), w = sc.nextInt();
            edge[i] = new Edge(a, b, w);
        }

        int t = bellmanFord(edge);
        if (t == -1) System.out.println("impossible");
        else System.out.println(t);
    }

    private static int bellmanFord(Edge[] edge) {
        Arrays.fill(dist, max);
        dist[1] = 0;

        // 进行 k 次 松弛操作
        while (k-- > 0) {
            //每次对dist数组进行一次备份
            //备份原因：每次只会从上一轮的结果里面去改动，不会被本轮改动的值所影响
            int[] copy = Arrays.copyOf(dist, N);
            //对每个边进行遍历，找到长度为k的最短距离
            for (int i = 1; i <= m; i++) {
                int a = edge[i].a, b = edge[i].b, w = edge[i].w;
                dist[b] = Math.min(copy[a] + w, dist[b]);
            }
        }

        // 为了防止出现从1到n其实没有路径，但是可以从max通过一个负值进行更新的情况
        if (dist[n] > max / 2) return -1;
        else return dist[n];
    }
}