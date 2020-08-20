package AcWing.基础算法.数据结构.N840模拟散列表;

/*
    维护一个集合，支持如下几种操作：

    “I x”，插入一个数x；
    “Q x”，询问数x是否在集合中出现过；
    现在要进行N次操作，对于每个询问操作输出对应的结果。

    输入格式
    第一行包含整数N，表示操作数量。

    接下来N行，每行包含一个操作指令，操作指令为”I x”，”Q x”中的一种。

    输出格式
    对于每个询问指令“Q x”，输出一个询问结果，如果x在集合中出现过，则输出“Yes”，否则输出“No”。

    每个结果占一行。

    数据范围
    1≤N≤10^5
    −10^9≤x≤10^9
    输入样例：
    5
    I 1
    I 2
    I 3
    Q 2
    Q 5
    输出样例：
    Yes
    No
 */

import java.io.*;
import java.util.Arrays;

public class Main2 {
    private static int INF = 1000_000_007;
    private static int N = 200003;
    static class myHash {
        int[] h;
        int n;
        public myHash(int n) {
            this.n = n;
            h = new int[this.n];
            Arrays.fill(h, INF);
        }

        /**
         *  找到第一个放 x 的位置
         * @param x
         * @return
         */
        public int find(int x) {
            int k = ((x % n) + n) % n;
            while (h[k] != INF && h[k] != x) {
                k++;
                if (k == n) k = 0;
            }
            return k;
        }

        public void insert(int x) {
            h[find(x)] = x;
        }

        public String query(int x) {
            return h[find(x)] == INF ? "No" : "Yes";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        myHash mh = new myHash(N);
        while (n-- > 0) {
            String[] s = reader.readLine().split(" ");
            int x = Integer.valueOf(s[1]);
            if (s[0].equals("I")) mh.insert(x);
            else log.write(mh.query(x) + "\n");
        }
        log.flush();
        log.close();
        reader.close();
    }
}