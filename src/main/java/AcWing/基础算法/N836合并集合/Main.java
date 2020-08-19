package AcWing.基础算法.N836合并集合;

/*
    一共有n个数，编号是1~n，最开始每个数各自在一个集合中。

    现在要进行m个操作，操作共有两种：

    “M a b”，将编号为a和b的两个数所在的集合合并，如果两个数已经在同一个集合中，则忽略这个操作；
    “Q a b”，询问编号为a和b的两个数是否在同一个集合中；
    输入格式
    第一行输入整数n和m。

    接下来m行，每行包含一个操作指令，指令为“M a b”或“Q a b”中的一种。

    输出格式
    对于每个询问指令”Q a b”，都要输出一个结果，如果a和b在同一集合内，则输出“Yes”，否则输出“No”。

    每个结果占一行。

    数据范围
    1≤n,m≤10^5
    输入样例：
    4 5
    M 1 2
    M 3 4
    Q 1 2
    Q 1 3
    Q 3 4
    输出样例：
    Yes
    No
    Yes
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static class UnionFind {
        int[] p;
        public UnionFind (int n) {
            p = new int[n + 1];
            for (int i = 1; i <= n; i++) p[i] = i;
        }

        public int find(int x) {
            if (x == p[x]) return x;
            return p[x] = find(p[x]); //路径压缩
        }

        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return;
            p[rootX] = rootY;
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
            int x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]);

            if (s[0].equals("M")) uf.union(x, y);
            else {
                if (uf.find(x) == uf.find(y)) System.out.println("Yes");
                else System.out.println("No");
            }
        }
    }
}
