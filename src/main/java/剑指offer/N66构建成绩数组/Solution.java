package 剑指offer.N66构建成绩数组;

import java.util.Arrays;

class Solution {
    public int[] constructArr(int[] a) {
        if (a == null || a.length == 0) return new int[]{};

        int len = a.length;
        int[] res = new int[len];
        Arrays.fill(res, 1);
        int tmp = 1;
        for (int i = 0; i < len; i++) {
            res[i] *= tmp;
            tmp *= a[i];
        }

        tmp = 1;
        for (int i = len - 1; i >= 0; i--) {
            res[i] *= tmp;
            tmp *= a[i];
        }

        return res;
    }
}
