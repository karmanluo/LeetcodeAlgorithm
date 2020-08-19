package AcWing.基础算法.N240食物链;

import java.io.*;

/*
    动物王国中有三类动物A,B,C，这三类动物的食物链构成了有趣的环形。
    A吃B， B吃C，C吃A。
    现有N个动物，以1－N编号。
    每个动物都是A,B,C中的一种，但是我们并不知道它到底是哪一种。
    有人用两种说法对这N个动物所构成的食物链关系进行描述：
    第一种说法是”1 X Y”，表示X和Y是同类。
    第二种说法是”2 X Y”，表示X吃Y。
    此人对N个动物，用上述两种说法，一句接一句地说出K句话，这K句话有的是真的，有的是假的。
    当一句话满足下列三条之一时，这句话就是假话，否则就是真话。
    1） 当前的话与前面的某些真的话冲突，就是假话；
    2） 当前的话中X或Y比N大，就是假话；
    3） 当前的话表示X吃X，就是假话。
    你的任务是根据给定的N和K句话，输出假话的总数。

    输入格式
    第一行是两个整数N和K，以一个空格分隔。
    以下K行每行是三个正整数 D，X，Y，两数之间用一个空格隔开，其中D表示说法的种类。
    若D=1，则表示X和Y是同类。
    若D=2，则表示X吃Y。

    输出格式
    只有一个整数，表示假话的数目。

    数据范围
    1≤N≤50000,
    0≤K≤100000
    输入样例：
    100 7
    1 101 1
    2 1 2
    2 2 3
    2 3 3
    1 1 3
    2 3 1
    1 5 5
    输出样例：
    3
 */
public class Main {
    private static int M = 3;
    static class UnionFind {
        int[] p;
        int[] d;
        public UnionFind(int n) {
            p = new int[n + 1];
            d = new int[n + 1];
            for (int i = 1; i <= n; i++) p[i] = i;
        }

        public int find(int x) {
            if (p[x] != x) {
                int u = find(p[x]);
                d[x] = (d[x] + d[p[x]]) % M;    // 注意这里一定要取模，不然这个距离可能会大于 M
                p[x] = u;
            }
            return p[x];
        }

        // 判断 x y 到底是不是同类
        public boolean D1(int x, int y) {
            int r1 = find(x), r2 = find(y);

            // x 和 y 已经处理
            if (r1 == r2) return d[x] % M == d[y] % M;

            p[r2] = r1;
            d[r2] = (d[x] - d[y] + M) % M;
            return true;
        }

        // x 能吃 y 吗 ？
        public boolean D2(int x, int y) {
            int r1 = find(x), r2 = find(y);

            if (r1 == r2) return d[x] % M == (d[y] + 1) % M;

            p[r2] = r1;
            d[r2] = ((d[x] - d[y] - 1) + M) % M;
            return true;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = read.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int k = Integer.parseInt(s[1]);

        UnionFind uf = new UnionFind(n);

        int res = 0;
        while (k-- > 0) {
            s = read.readLine().split(" ");
            int t = Integer.parseInt(s[0]), x = Integer.parseInt(s[1]), y = Integer.parseInt(s[2]);

            if (x > n || y > n) res++;
            else if (t == 1) res += (uf.D1(x, y) == true ? 0 : 1);
            else if (t == 2) res += (uf.D2(x, y) == true ? 0 : 1);
        }
        log.write(res + "\n");
        log.flush();
    }
}