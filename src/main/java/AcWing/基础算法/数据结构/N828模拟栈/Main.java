package AcWing.基础算法.数据结构.N828模拟栈;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    private static int N = 100010;
    private static int tt = 0;
    private static int[] stack = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());
        while (m-- > 0) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("push")) {
                stack[++tt] = Integer.parseInt(s[1]);
            } else if (s[0].equals("pop")) {
                tt--;
            } else if (s[0].equals("empty")) {
                if (tt == 0) System.out.println("YES");
                else System.out.println("NO");
            }
            else System.out.println(stack[tt]);
        }
    }
}
