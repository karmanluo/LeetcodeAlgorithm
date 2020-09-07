package AcWing.基础算法.搜索与图论.N851SPFA求最短路径;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    给定一个 n 个点 m 条边的 有向图，图中可能存在重边和自环， 边权可能为负数。
    请你求出 1 号点到 n 号点的最短距离，如果无法从1号点走到n号点，则输出impossible。
    数据保证不存在负权回路。

    输入格式
    第一行包含整数n和m。
    接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

    输出格式
    输出一个整数，表示1号点到n号点的最短距离。

    如果路径不存在，则输出”impossible”。

    数据范围
    1≤n,m≤10^5,
    图中涉及边长绝对值均不超过10000。

    输入样例：
    3 3
    1 2 5
    2 3 -3
    1 3 4
    输出样例：
    2
 */
public class Main {

    static int N = 100010, max = 0x3f3f3f3f;
    static int[] h = new int[N], e = new int[N], ne = new int[N], w = new int[N];
    static int idx;
    static int n, m;
    static int[] dist = new int[N];
    // st 用来记录哪些点现在在队列里面，如果在里面不需要重复添加。减少不必要的计算
    static boolean[] st = new boolean[N];

    public static void main(String[] args) {
        // 思想： 用一个队列存更新过的点，因为只有更新过的位置才可能进一步的更新
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        Arrays.fill(h, -1);
        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            add(a, b, c);
        }

        int res = spfa();
        if (res == -1) System.out.println("impossible");
        else System.out.println(res);
    }

    private static int spfa() {
        Arrays.fill(dist, max);
        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    if (!st[j]) {
                        q.offer(j);
                        st[j] = true;
                    }
                }
            }
        }

        if (dist[n] == max) return -1;
        return dist[n];
    }

    private static void add(int a, int b, int c) {
        e[idx] = b; ne[idx] = h[a]; w[idx] = c; h[a] = idx++;
    }
}
