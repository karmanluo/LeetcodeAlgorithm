package LeetcodeAlgorithm.N501_600.N528按权重进行选择;

import java.util.Random;

public class Solution {

    int[] pSum;
    int n;
    int total = 0;
    Random random = new Random();

    public Solution(int[] w) {
        n = w.length;
        pSum = new int[n];
        for (int i = 0; i < n; i++) {
            total += w[i];
            pSum[i] = total;
        }
    }

    public int pickIndex() {
        int target = random.nextInt(total) + 1;
        int l = 0, r = n - 1;

        while (l < r) {
            int m = l + r >> 1;
            if (pSum[m] < target) l = m + 1;
            else r = m;
        }
        return l;
    }
}
