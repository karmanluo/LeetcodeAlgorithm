package Test.格式;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 19:13
 */
public class Test4_20_1 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int num = in.nextInt();
        int[] n = new int[num];
        for (int i = 0; i < n.length; i++) {
            n[i] = in.nextInt();
        }

        System.out.println(fight(a, n));
    }

    public static int fight(int a, int[] n) {
        int maxCoin = 0;
        int coins = 0;
        Arrays.sort(n);


        for (int i = 0; i < n.length; i++) {
            if (a >= n[i]) {
                coins++;
                maxCoin = Math.max(coins, maxCoin);
            } else {
                if (coins - n[i] >= 0) {
                    coins = coins - n[i];
                    coins++;
                    a = n[i];
                    maxCoin = Math.max(coins, maxCoin);
                }
                break;
            }
        }

        return maxCoin;
    }

}

