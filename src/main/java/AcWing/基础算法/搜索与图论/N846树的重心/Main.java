package AcWing.基础算法.搜索与图论.N846树的重心;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static boolean[] st;
    static int ans;
    static int N;
    static class Graph {
        int[] h, e, ne;
        int idx;
        int n;
        public Graph(int n) {
            this.n = n;
            h = new int[n];
            e = new int[2 * n];
            ne = new int[2 * n];
            Arrays.fill(h, -1);
        }

        public void add(int a, int b) {
            e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ans = N;
        Graph g = new Graph(N + 1);
        st = new boolean[N + 1];

        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt(), b = sc.nextInt();
            g.add(a, b);
            g.add(b, a);
        }

        dfs(g, 1);
        System.out.println(ans);
    }

    // 以 u 为跟的节点数之和
    private static int dfs(Graph g, int u) {
        st[u] = true; // 已经遍历第u号节点

        int sum = 0, size = 0; // sum 表示 u 节点下节点的总数， size 表示去掉u之后最大的块
        for (int i = g.h[u]; i != -1; i = g.ne[i]) {
            int j = g.e[i];
            if (st[j]) continue;

            int s = dfs(g, j);
            size = Math.max(size, s);
            sum += s;

        }
        size = Math.max(size, N - sum - 1);
        ans = Math.min(ans, size);

        return sum + 1;
    }
}