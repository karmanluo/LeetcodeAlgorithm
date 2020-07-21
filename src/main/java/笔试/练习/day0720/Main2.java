package 笔试.练习.day0720;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

/**
 * 小Q在周末的时候和他的小伙伴来到大城市逛街，一条步行街上有很多高楼，共有n座高楼排成一行。
 * 小Q从第一栋一直走到了最后一栋，小Q从来都没有见到这么多的楼，所以他想知道他在每栋楼的位置处能看到多少栋楼呢？
 * （当前面的楼的高度大于等于后面的楼时，后面的楼将被挡住）
 *
 *输入描述:
 * 输入第一行将包含一个数字n，代表楼的栋数，接下来的一行将包含n个数字wi(1<=i<=n)，代表每一栋楼的高度。
 * 1<=n<=100000;
 * 1<=wi<=100000;
 *
 * 输出描述:
 * 输出一行，包含空格分割的n个数字vi，分别代表小Q在第i栋楼时能看到的楼的数量。
 *
 * 输入例子1:
 * 6
 * 5 3 8 3 2 5
 *
 * 输出例子1:
 * 3 3 5 4 4 4
 *
 * 例子说明1:
 * 当小Q处于位置3时，他可以向前看到位置2,1处的楼，向后看到位置4,6处的楼，加上第3栋楼，共可看到5栋楼。当小Q处于位置4时，他可以向前看到位置3处的楼，
 * 向后看到位置5,6处的楼，加上第4栋楼，共可看到4栋楼。
 *
 */

public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int bulidingNum = sc.nextInt();
        int[] height = new int[bulidingNum];
        int[] ans = new int[bulidingNum];
        for (int i = 0; i < bulidingNum; i++) {
            height[i] = sc.nextInt();
        }

        Deque<Integer> L = new ArrayDeque<Integer>();
        for (int i = 0; i < bulidingNum; i++) {
            ans[i] = L.size();
            if (i != 0) {
                while (!L.isEmpty() && height[i] >= L.peek()) L.pop();
            }
            L.push(height[i]);
        }

        Deque<Integer> R = new ArrayDeque<>();
        for (int i = bulidingNum - 1; i >= 0; i--) {
            ans[i] += R.size();
            if (i != bulidingNum - 1) {
                while (!R.isEmpty() && height[i] >= R.peek()) R.pop();
            }
            R.push(height[i]);
        }

        for (int i = 0; i < bulidingNum; i++) {
            System.out.print((ans[i] + 1) + " ");
        }
    }
}
