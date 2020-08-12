package AcWing.基础算法.数据结构.N826单链表;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    //head 表示头结点的指针
    //e[i] 表示节点i的值
    //ne[i] 表示节点i的下一个节点的位置
    //idx 存储当前已经用到了哪个位置
    private static int N = 100010;
    private static int head, idx;
    private static int[] e = new int[N];
    private static int[] ne = new int[N];

    // 初始化
    private static void init() {
        head = -1;
        idx = 0;
    }

    //将 val 插入到头结点
    private static void insertToHead(int val) {
        e[idx] = val;
        ne[idx] = head;
        head = idx;
        idx++;
    }

    // 插入节点到 k 节点的后面
    private static void insert(int k, int val) {
        e[idx] = val;
        ne[idx] = ne[k];
        ne[k] = idx;
        idx++;
    }

    // 将下标k之后的一个节点删除
    private static void remove(int k) {
        ne[k] = ne[ne[k]];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(br.readLine());

        init();

        while (m -- > 0) {
            String[] s = br.readLine().split(" ");
            if (s[0].equals("H")) {             //必须双引号
                int x = Integer.parseInt(s[1]);
                insertToHead(x);
            } else if (s[0].equals("D")) {
                int k = Integer.parseInt(s[1]);
                if (k == 0) head = ne[head];
                else remove(k - 1);
            } else if (s[0].equals("I")) {
                int k = Integer.parseInt(s[1]);
                int x = Integer.parseInt(s[2]);
                insert(k - 1, x);
            }
        }

        for (int i = head; i != -1; i = ne[i]) System.out.print(e[i] + " ");
    }
}