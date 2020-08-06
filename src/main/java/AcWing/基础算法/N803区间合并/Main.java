package AcWing.基础算法.N803区间合并;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/*
        给定 n 个区间 [li,ri]，要求合并所有有交集的区间。

        注意如果在端点处相交，也算有交集。

        输出合并完成后的区间个数。

        例如：[1,3]和[2,6]可以合并为一个区间[1,6]。

        输入格式
        第一行包含整数n。

        接下来n行，每行包含两个整数 l 和 r。

        输出格式
        共一行，包含一个整数，表示合并区间完成后的区间个数。

        数据范围
        1≤n≤100000,
        −10^9≤li≤ri≤10^9
        输入样例：
        5
        1 2
        2 4
        5 6
        7 8
        7 9
        输出样例：
        3
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);
        System.out.println(getNum(arr));
    }

    private static int getNum(int[][] arr) {
        List<int[]> res = new ArrayList<>();

        int[] temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i][0] > temp[1]) {
                res.add(temp);
                temp = arr[i];
            }
            else temp[1] = Math.max(temp[1], arr[i][1]);
        }
        res.add(temp);

        return res.size();
    }
}
