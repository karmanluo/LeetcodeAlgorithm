package AcWing.基础算法.N794高精度除法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    给定两个非负整数A，B，请你计算 A / B的商和余数。

    输入格式
    共两行，第一行包含整数A，第二行包含整数B。

    输出格式
    共两行，第一行输出所求的商，第二行输出所求余数。

    数据范围
    1≤A的长度≤100000,
    1≤B≤10000
    B 一定不为0

    输入样例：
    7
    2
    输出样例：
    3
    1
 */
public class Main {

    static int r = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        int b = Integer.parseInt(br.readLine());

        int[] A = new int[a.length()];
        for (int i = 0; i < a.length(); i++) A[i] = a.charAt(i) - '0';

        int[] res = div(A, b);
        for (int re : res) System.out.print(re);
        System.out.println("\n" + r);
    }

    private static int[] div(int[] A, int b) {
        int n = A.length;
        int[] C = new int[n];

        for (int i = 0; i < n; i++) {
            r = r * 10 + A[i];
            C[i] = r / b;
            r %= b;
        }
        // 去掉0
        int index = 0;
        for (int i = 0; i < n; i++) {
            if (C[i] != 0) {
                index = i;
                break;
            }
        }

        int[] res = new int[n - index];
        System.arraycopy(C, index, res, 0, n - index);

        return res;
    }
}