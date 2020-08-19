package AcWing.基础算法.数据结构.N830单调栈;

import java.util.Scanner;

// 单调栈的应用场景 , 每一个数左边/右边离他最近的数
public class Main {

    private static int N = 10_0010;
    private static int tt = 0;
    private static int[] stack = new int[N];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        while (n-- > 0) {
            int x = sc.nextInt();
            while (tt > 0 && stack[tt] >= x) tt--;
            if (tt > 0) System.out.print(stack[tt] + " ");
            else System.out.print(-1 + " ");

            stack[++tt] = x;
        }
    }
}
