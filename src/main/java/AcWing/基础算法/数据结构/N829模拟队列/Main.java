package AcWing.基础算法.数据结构.N829模拟队列;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int hh = 0, tt = -1;
    private static int N = 10_0010;
    private static int[] q = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("push")) {
                q[++tt] = Integer.parseInt(s[1]);
            } else if (s[0].equals("pop")) {
                hh++;
            } else if (s[0].equals("empty")) {
                if (hh <= tt) System.out.println("NO");
                else System.out.println("YES");
            } else if (s[0].equals("query")) {
                System.out.println(q[hh]);
            }
        }
    }
}
