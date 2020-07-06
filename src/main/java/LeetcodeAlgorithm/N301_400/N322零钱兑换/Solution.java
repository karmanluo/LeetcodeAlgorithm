package LeetcodeAlgorithm.N301_400.N322零钱兑换;

import java.util.Arrays;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/8 18:04
 */
public class Solution {
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(0, amount, coins, coins.length - 1); //尽量使用大额硬币

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    private void helper(int count, int amount, int[] coins, int start) {
        if (amount == 0) { //找到一种组合
            if (count < res) res = count;
            return;
        }
        if (start == -1 || amount / coins[start] + count >= res) return;

        for (int j = amount / coins[start]; j >= 0; j--) {
            helper(count + j, amount - coins[start] * j, coins, start - 1);
        }
    }

}