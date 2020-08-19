package AcWing.基础算法.N143最大异或对;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    在给定的N个整数A1，A2……AN中选出两个进行xor（异或）运算，得到的结果最大是多少？

    输入格式
    第一行输入一个整数N。

    第二行输入N个整数A1～AN。

    输出格式
    输出一个整数表示答案。

    数据范围
    1≤N≤10^5,
    0≤Ai<2^31
    输入样例：
    3
    1 2 3
    输出样例：
    3
 */
public class Main {
    /**
     *     暴力会超时，进行优化
     */
    static class Trie {
        int[][] son;
        int idx;
        public Trie(int n) {
            son = new int[31 * n][2];
            idx = 0;
        }

        public void insert(int x) {
            int p = 0;
            for (int i = 30; i >= 0; i--) {
                int cur = (x >> i) & 1;
                if (son[p][cur] == 0) son[p][cur] = ++idx;
                p = son[p][cur];
            }
        }

        //匹配到的值
        public int query(int x) {
            int p = 0, res = 0;
            for (int i = 30; i >= 0; i--) {
                int u = (x >> i) & 1;
                if (son[p][1- u] != 0) {
                    p = son[p][1- u];
                    res = res * 2 + 1 - u;
                } else {
                    p = son[p][u];
                    res = res * 2 + u;
                }
            }

            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] a = new int[n];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < n; i++) a[i] = Integer.parseInt(s[i]);

        Trie trie = new Trie(n);

        int res = 0;
        for (int i = 0; i < n; i++) {
            trie.insert(a[i]);
            res = Math.max(res, trie.query(a[i]) ^ a[i]);
        }

        System.out.println(res);
    }
}
