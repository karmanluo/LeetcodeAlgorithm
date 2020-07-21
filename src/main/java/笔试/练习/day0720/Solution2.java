package 笔试.练习.day0720;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int l = sc.nextInt();
        int r = sc.nextInt();

        System.out.println(helper(l, r));
    }

    private static int helper(int l, int r) {
        boolean[] dp = new boolean[r + 1];
        int res_l = 0;
        int res = 0;

        for (int i = 1; i <= r; i++) {
            if (i < 10) {
                dp[i] = i == 7 ? true : false;
            } else {
                int temp = reduce(i);
                dp[i] = dp[temp];
            }
            if (dp[i]) {
                res++;
                if (i <= l)
                    res_l++;
            }


        }
        return res - res_l;
    }

    public static int reduce(int l) {
        List<Integer> res = new ArrayList<>();
        while (l > 0) {
            res.add(l % 10);
            l /= 10;
        }
        int result = 0;
        for (int i = res.size() - 1; i >= 1; i--) {
            result = result * 10 + Math.abs(res.get(i) - res.get(i - 1));
        }
        return result;

    }

}