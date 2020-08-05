package AcWing.基础算法.N802区间和;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        −109≤x≤10^9,
        1≤n,m≤10^5,
        −109≤l≤r≤10^9,
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
    static class BinaryIndexedTree {

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] line = br.readLine().split(" ");
        int n = Integer.parseInt(line[0]), m = Integer.parseInt(line[1]);


    }
}
