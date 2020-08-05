package AcWing.基础算法.N802区间和;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/*
        假定有一个无限长的数轴，数轴上每个坐标上的数都是0。

        现在，我们首先进行 n 次操作，每次操作将某一位置x上的数加c。

        接下来，进行 m 次询问，每个询问包含两个整数l和r，你需要求出在区间[l, r]之间的所有数的和。

        输入格式
        第一行包含两个整数n和m。

        接下来 n 行，每行包含两个整数x和c。

        再接下里 m 行，每行包含两个整数l和r。

        输出格式
        共m行，每行输出一个询问中所求的区间内数字和。

        数据范围
        −10^9≤x≤10^9,
        1≤n,m≤10^5,
        −10^9≤l≤r≤10^9,
        −10000≤c≤10000
        输入样例：
        3 3
        1 2
        3 6
        7 5
        1 3
        4 6
        7 8
        输出样例：
        8
        0
        5
 */
public class Main {
    static int N = 1000_10;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // n 次操作
        int m = sc.nextInt(); // m 次询问
        int N = 30_0010;      // 需要将所有 x, l, r 存入数组, 这样 n + 2m <= 30_0000
        int[] a = new int[N]; // 从 1 开始，需要通过 x 找到离散量，然后 + 1
        int[] s = new int[N]; //前缀和来做，从 1 开始记录 a

        List<Integer> alls = new ArrayList<>(); //存的是所有需要离散化的值，x l r
        List<Pairs> add = new ArrayList<>();
        List<Pairs> query = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt(), c = sc.nextInt();
            add.add(new Pairs(x, c));
            alls.add(x);
        }

        for (int i = 0; i < m; i++) {
            int l = sc.nextInt(), r = sc.nextInt();
            query.add(new Pairs(l ,r));
            alls.add(l);
            alls.add(r);
        }

        //到此为止，alls中存好了所有会被用到的数轴上的点，可以进行离散化操作
        // 1. 排序 2. 去重

        Collections.sort(alls);
        int unique = unique(alls);      // unique 代表不重复结束位置的下一个位置
        alls = alls.subList(0, unique);

        //处理插入
        for (Pairs p : add) {
            int x = find(p.first, alls); //给位置离散化
            a[x] += p.second;      //把实际的值存在离散化之后的位置
        }

        //预处理前缀和
        for (int i = 0; i <= alls.size(); i++) s[i] = s[i - 1] + a[i];

        //处理询问
        for (Pairs p : query) {
            int l = find(p.first, alls), r = find(p.second, alls);
            System.out.println(s[r] - s[l - 1]);
        }

    }

    private static int find(int x, List<Integer> list) {
        int l = 0, r = list.size() - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (list.get(mid) >= x) r = mid;
            else l = mid + 1;
        }

        return l + 1; // 由于前缀和的index后移了
    }

    private static int unique(List<Integer> list) {
        int j = 0;
        for (int i = 0; i < list.size(); i++) {
            if (i == 0 || list.get(i) != list.get(i - 1)) {
                list.set(j, list.get(i));
                j++;
            }
        }

        return j;
    }

    static class Pairs {
        int first;
        int second;
        public Pairs(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}
