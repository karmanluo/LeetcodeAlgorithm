package 笔试.练习.day0720;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 小Q在进行一场竞技游戏,这场游戏的胜负关键就在于能否能争夺一条长度为L的河道,即可以看作是[0,L]的一条数轴。
 * 这款竞技游戏当中有n个可以提供视野的道具−真视守卫,第i个真视守卫能够覆盖区间[xi,yi]。现在小Q想知道至少用几个真视守卫就可以覆盖整段河道。
 *
 * 输入描述:
 * 输入包括n+1行。
 *
 * 第一行包括两个正整数n和L(1<=n<=105,1<=L<=109)
 *
 * 接下来的n行,每行两个正整数xi,yi(0<=xi<=yi<=109),表示第i个真视守卫覆盖的区间。
 *
 *
 * 输出描述:
 * 一个整数，表示最少需要的真视守卫数量, 如果无解, 输出-1。
 *
 * 输入例子1:
 * 4 6
 * 3 6
 * 2 4
 * 0 2
 * 4 7
 *
 * 输出例子1:
 * 3
 */

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int len = sc.nextInt();

        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = sc.nextInt();
            arr[i][1] = sc.nextInt();
        }

        System.out.println(minEyesCount(arr, len));
    }

    private static int minEyesCount(int[][] arr, int len) {
        Arrays.sort(arr, (a, b)->{return (a[0] == b[0]) ? (a[1] - b[1]) : (a[0] - b[0]);});

        int count = 0;
        int startPos = 0;
        int newPos = 0;
        int i = 0;
        while (i < arr.length) {
            startPos = newPos;
            for (; i < arr.length && arr[i][0] <= startPos; i++) newPos = Math.max(newPos, arr[i][1]);
            count++;

            if (newPos >= len) return count;
        }

        return -1;
    }
}
