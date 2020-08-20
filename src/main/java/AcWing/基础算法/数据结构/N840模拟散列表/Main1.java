package AcWing.基础算法.数据结构.N840模拟散列表;

import java.io.*;
import java.util.Arrays;

public class Main1 {
    //拉链表法模拟hash
    public static int N = 100003;

    static class AdjacentList {
        int idx;
        int[] e, ne;
        int[] h;
        public AdjacentList(int n) {
            e = new int[n];
            ne = new int[n];
            h = new int[n];
            Arrays.fill(h, -1);
            idx = 0;
        }

        public void insert(int x) {
            int k = ((x % N) + N) % N;
            e[idx] = x; ne[idx] = h[k]; h[k] = idx++;
        }

        public boolean find(int x) {
            int k = ((x % N) + N) % N;
            for (int i = h[k]; i != -1; i = ne[i]) {
                if (e[i] == x) return true;
            }
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        AdjacentList ajl = new AdjacentList(N);

        while (n-- > 0) {
            String[] s = reader.readLine().split(" ");
            int x = Integer.valueOf(s[1]);
            if (s[0].equals("I")) ajl.insert(x);
            else {
                String res = (ajl.find(x) == true ? "Yes" : "No");
                log.write(res + "\n");
            }
        }
        log.flush();
        log.close();
        reader.close();
    }
}