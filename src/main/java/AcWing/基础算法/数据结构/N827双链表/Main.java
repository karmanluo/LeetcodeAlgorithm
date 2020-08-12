package AcWing.基础算法.数据结构.N827双链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    private static int N = 100010;
    private static int[] e = new int[N]; //每个节点存储的值是多少
    private static int[] l = new int[N]; //每个节点左边的节点
    private static int[] r = new int[N]; //每个节点右边的节点
    private static int idx = 0;

    public static void init() {
        r[0] = 1;
        l[1] = 0;
        idx = 2;
    }

    //在index后加一个节点
    public static void add(int k, int x) {
        e[idx] = x;
        l[r[k]] = idx;
        r[idx] = r[k];
        r[k] = idx;
        l[idx] = k;
        idx++;
    }

    //删除idx为k的节点
    public static void remove(int k) {
        r[l[k]] = r[k];
        l[r[k]] = l[k];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int m = Integer.parseInt(br.readLine());

        init();

        while (m-- > 0) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("R")) {
                int x = Integer.parseInt(s[1]);
                add(l[1], x);
            } else if (s[0].equals("L")) {
                int x = Integer.parseInt(s[1]);
                add(0, x);
            } else if (s[0].equals("D")) {
                int dIdx = Integer.parseInt(s[1]);
                remove(dIdx + 1);
            } else if (s[0].equals("IL")) {
                int iIdex = Integer.parseInt(s[1]);
                int x = Integer.parseInt(s[2]);
                add(l[iIdex + 1], x);
            } else if (s[0].equals("IR")) {
                int iIdex = Integer.parseInt(s[1]);
                int x = Integer.parseInt(s[2]);
                add(iIdex + 1, x);
            }
        }

        for (int i = r[0]; i != 1; i = r[i]) {
            System.out.print(e[i] + " ");
        }

    }
}
