package AcWing.基础算法.搜索与图论.N847图中点的层次;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

    static int N;
    static class Graph {
        int[] e, ne, h;
        int idx;
        int n;
        public Graph (int n) {
            this.n = n;
            e = new int[n * 10];
            ne = new int[n * 10];
            idx = 0;
            h = new int[n];
            Arrays.fill(h, -1);
        }
        public void add(int a, int b) {
            e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int m = sc.nextInt();
        Graph g = new Graph(N + 1);

        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt();
            g.add(a, b);
        }

        System.out.println(bfs(g, 1));
    }

    private static int bfs(Graph g, int u) {
        int[] d = new int[N + 1];
        Arrays.fill(d, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(u);
        d[u] = 0;
        while (!q.isEmpty()) {
            int t = q.poll();
            for (int i = g.h[t]; i != -1; i = g.ne[i]) {
                int j = g.e[i];
                if (d[j] != -1) continue;
                d[j] = d[t] + 1;
                q.offer(j);
            }
        }

        return d[N];
    }
}