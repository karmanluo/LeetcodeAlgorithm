package AcWing.基础算法.数据结构.N841字符串哈希;

import java.io.*;

// 字符串哈希，解决字符串问题
public class Main {

    private static int P = 131;
    static class myStringHash {
        int[] p, h;
        int N;
        public myStringHash(String s, int n) {
            N = n;
            p = new int[n + 1];
            h = new int[n + 1];
            p[0] = 1;
            for (int i = 1; i <= n; i++) {
                p[i] = p[i - 1] * P;
                h[i] = h[i - 1] * P + s.charAt(i - 1);
            }
        }

        public long getStringHash(int l, int r) {
            return h[r] - h[l - 1] * p[r - l + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter log = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] s = reader.readLine().split(" ");
        int n = Integer.valueOf(s[0]);
        int m = Integer.valueOf(s[1]);

        String w = reader.readLine();
        myStringHash msh = new myStringHash(w, n);

        while(m-- > 0) {
            s = reader.readLine().split(" ");
            int l1 = Integer.valueOf(s[0]), l2 = Integer.valueOf(s[2]);
            int r1 = Integer.valueOf(s[1]), r2 = Integer.valueOf(s[3]);
            String str = msh.getStringHash(l1, r1) == msh.getStringHash(l2, r2) ? "Yes" : "No";
            log.write(str + "\n");
        }
        log.flush();
    }
}