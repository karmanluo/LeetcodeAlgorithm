package AcWing.基础算法.搜索与图论.N848有向图的拓扑序列;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static int N = 100010;
    static int n, m;
    static int[] h = new int[N], e = new int[N], ne = new int[N];
    static int idx = 0;
    static int[] d = new int[N], q = new int[N]; //d记录下入度

    public static void add(int a, int b) {
        e[idx] = b; ne[idx] = h[a]; h[a] = idx++;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Arrays.fill(h, -1);

        n = sc.nextInt();
        m = sc.nextInt();
        while (m-- > 0) {
            int a = sc.nextInt(), b = sc.nextInt();
            add(a, b);
            d[b]++;
        }

        if (!topsort()) System.out.println(-1);
        else {
            for (int i = 0; i < n; i++) {
                System.out.print(q[i] + " ");
            }
        }
    }

    private static boolean topsort() {
        int hh = 0, tt = -1;

        for (int i = 1; i <= n; i++) {
            if (d[i] == 0) q[++tt] = i;
        }

        while (hh <= tt) {
            int t = q[hh++];

            for (int i = h[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (--d[j] == 0) {
                    q[++tt] = j;
                }
            }
        }

        return tt == n - 1;
    }
}
