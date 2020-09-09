package AcWing.基础算法.搜索与图论.N854Floyd求最短路径;

import java.util.Scanner;

/*
    给定一个n个点m条边的有向图，图中可能存在重边和自环，边权可能为负数。
    再给定k个询问，每个询问包含两个整数x和y，表示查询从点x到点y的最短距离，如果路径不存在，则输出“impossible”。
    数据保证图中不存在负权回路。

    输入格式
    第一行包含三个整数n，m，k
    接下来m行，每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。
    接下来k行，每行包含两个整数x，y，表示询问点x到点y的最短距离。

    输出格式
    共k行，每行输出一个整数，表示询问的结果，若询问两点间不存在路径，则输出“impossible”。

    数据范围
    1≤n≤200,
    1≤k≤n^2
    1≤m≤20000,
    图中涉及边长绝对值均不超过10000。

    输入样例：
    3 3 2
    1 2 1
    2 3 2
    1 3 1
    2 1
    1 3
    输出样例：
    impossible
    1
 */
public class Main {
    /*解题思路，动态规划的思想
    假设节点序号是从1到n。
    假设f[0][i][j]是一个n*n的矩阵，第i行第j列代表从i到j的权值，如果i到j有边，那么其值就为ci,j（边ij的权值）。
    如果没有边，那么其值就为无穷大。

    f[k][i][j]代表（k的取值范围是从1到n），在考虑了从1到k的节点作为中间经过的节点时，从i到j的最短路径的长度。

    比如，f[1][i][j]就代表了，在考虑了1节点作为中间经过的节点时，从i到j的最短路径的长度。
    分析可知，f[1][i][j]的值无非就是两种情况，而现在需要分析的路径也无非两种情况，i=>j，i=>1=>j：
    【1】f[0][i][j]：i=>j这种路径的长度，小于，i=>1=>j这种路径的长度
    【2】f[0][i][1]+f[0][1][j]：i=>1=>j这种路径的长度，小于，i=>j这种路径的长度
    形式化说明如下：
    f[k][i][j]可以从两种情况转移而来：
    【1】从f[k−1][i][j]转移而来，表示i到j的最短路径不经过k这个节点
    【2】从f[k−1][i][k]+f[k−1][k][j]转移而来，表示i到j的最短路径经过k这个节点

    总结就是：f[k][i][j]=min(f[k−1][i][j],f[k−1][i][k]+f[k−1][k][j])
    从总结上来看，发现f[k]只可能与f[k−1]有关。
    */
    static int N = 210;
    static int n, m, q;
    static int[][] d = new int[N][N];
    static int INF = (int)1e9;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt(); q = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) d[i][j] = 0;
                else d[i][j] = INF;
            }
        }

        for(int i = 0; i < m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), w = sc.nextInt();
            d[a][b] = Math.min(d[a][b], w);
        }

        Floyd();

        while (q-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt();
            if (d[a][b] > INF / 2) System.out.println("impossible");
            else System.out.println(d[a][b]);
        }
    }

    private static void Floyd() {
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
    }
}
