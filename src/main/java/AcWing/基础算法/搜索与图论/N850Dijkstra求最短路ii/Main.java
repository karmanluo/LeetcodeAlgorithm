package AcWing.基础算法.搜索与图论.N850Dijkstra求最短路ii;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/*
    给定一个n个点m条边的有向图，图中可能存在重边和自环，所有边权均为非负值。
    请你求出1号点到n号点的最短距离，如果无法从1号点走到n号点，则输出-1。

    输入格式
    第一行包含整数n和m。
    接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

    输出格式
    输出一个整数，表示1号点到n号点的最短距离。
    如果路径不存在，则输出-1。

    数据范围
    1≤n,m≤1.5×10^5,
    图中涉及边长均不小于0，且不超过10000。

    输入样例：
    3 3
    1 2 2
    2 3 1
    1 3 4
    输出样例：
    3
 */
public class Main {
    // 从题目来看，n(点)和m(边)的数量级在一个，是稀疏图，可以用邻接表来存
    static int N = 1_50010;
    static int m, n, idx;
    static int[] h = new int[N], w = new int[N], e = new int[N], ne = new int[N];
    static int[] dist = new int[N];
    static boolean[] st = new boolean[N];

    static void add(int a, int b, int c) {
        e[idx] = b; w[idx] = c; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();

        Arrays.fill(h, -1);

        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            add(a, b, c);
        }

        System.out.println(dijkstra());
    }

    private static int dijkstra() {
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[1] = 0;

        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b)-> a[1] - b[1]); //小顶堆
        heap.offer(new int[]{1, 0});

        while (!heap.isEmpty()) {
            int[] t = heap.poll();
            int ver = t[0], distance = t[1];

            if (st[ver]) continue;
            st[ver] = true;

            // 更新和最小节点相连的点
            for (int i = h[ver]; i != -1; i = ne[i]) {
                int j = e[i]; //节点号
                if (dist[j] > distance + w[i]) {
                    dist[j] = distance + w[i];
                    heap.offer(new int[]{j, dist[j]});
                }
            }
        }

        if (dist[n] == 0x3f3f3f3f) return -1;
        else return dist[n];
    }
}