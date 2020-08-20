package AcWing.基础算法.数据结构.N837连通块中点的数量;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    给定一个包含n个点（编号为1~n）的无向图，初始时图中没有边。

    现在要进行m个操作，操作共有三种：

    “C a b”，在点a和点b之间连一条边，a和b可能相等；
    “Q1 a b”，询问点a和点b是否在同一个连通块中，a和b可能相等；
    “Q2 a”，询问点a所在连通块中点的数量；
    输入格式
    第一行输入整数n和m。

    接下来m行，每行包含一个操作指令，指令为“C a b”，“Q1 a b”或“Q2 a”中的一种。

    输出格式
    对于每个询问指令”Q1 a b”，如果a和b在同一个连通块中，则输出“Yes”，否则输出“No”。

    对于每个询问指令“Q2 a”，输出一个整数表示点a所在连通块中点的数量

    每个结果占一行。

    数据范围
    1≤n,m≤10^5
    输入样例：
    5 5
    C 1 2
    Q1 1 2
    Q2 1
    C 2 5
    Q2 5
    输出样例：
    Yes
    2
    3
 */
public class Main {
    static class UnionFind {
        int[] p;
        int[] size;
        public UnionFind(int n) {
            p = new int[n + 1];
            size = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                p[i] = i;
                size[i] = 1;
            }
        }

        public int find(int x) {
            if (x != p[x]) p[x] = find(p[x]);
            return p[x];
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;

            if (size[rootX] > size[rootY]) {
                p[rootY] = rootX;
                size[rootX] += size[rootY];
            } else {
                p[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }

        public int getSize(int x) {
            return size[find(x)];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);

        UnionFind uf = new UnionFind(n);

        while (m-- > 0) {
            s = br.readLine().split(" ");
            if (s[0].equals("C")) uf.union(Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            else if (s[0].equals("Q2")) System.out.println(uf.getSize(Integer.parseInt(s[1])));
            else {
                int x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]);
                if (uf.find(x) == uf.find(y)) System.out.println("Yes");
                else System.out.println("No");
            }
        }
    }
}
