package AcWing.基础算法.搜索与图论.N852SPFA判断负环;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
    给定一个n个点m条边的有向图，图中可能存在重边和自环， 边权可能为负数。
    请你判断图中是否存在负权回路。

    输入格式
    第一行包含整数n和m。
    接下来m行每行包含三个整数x，y，z，表示存在一条从点x到点y的有向边，边长为z。

    输出格式
    如果图中存在负权回路，则输出“Yes”，否则输出“No”。

    数据范围
    1≤n≤2000,
    1≤m≤10000,
    图中涉及边长绝对值均不超过10000。

    输入样例：
    3 3
    1 2 -1
    2 3 4
    3 1 -4
    输出样例：
    Yes
 */
public class Main {
    static int N = 10010;
    static int[] ne = new int[N], e = new int[N], h = new int[N], w = new int[N];
    static int idx, n, m;
    static boolean[] st = new boolean[N]; // st[i] 要来表示节点i是否在队列里面
    static int[] cnt = new int[N];  // cnt[i] 表示到某个点最短路的边数
    static int[] dist = new int[N];

    static void add(int a, int b, int c) {
        e[idx] = b; w[idx] = c; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String[] args) {
        Arrays.fill(h, -1);
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt(); m = sc.nextInt();
        for (int i = 1; i <= m; i++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            add(a, b, c);
        }

        if (spfa()) System.out.println("Yes");
        else System.out.println("No");
    }

    private static boolean spfa() {
        // 应为负环不一定是从1点开始的路径上，所以要加入所有的点
        // 因为我们求的不是有没有负环，所以 dist[] 不需要初始化为最大值，可以画一个图模拟
        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) q.offer(i);

        while (!q.isEmpty()) {
            int t = q.poll();
            st[t] = false;

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] > dist[t] + w[i]) {
                    dist[j] = dist[t] + w[i];
                    cnt[j] = cnt[t] + 1;
                    if (cnt[j] >= n) return true;

                    if (!st[j]) {
                        st[j] = true;
                        q.offer(j);
                    }
                }
            }
        }

        return false;
    }
}
