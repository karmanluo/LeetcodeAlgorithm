package LeetcodeAlgorithm.N401_500.N440字典序第K小数字;

public class Solution {
    public int findKthNumber(int n, int k) {
        k = k - 1;
        int curr = 1;
        while (k > 0) {
            int num = getNum(n, curr, curr + 1);
            if (num <= k) {
                curr += 1;
                k -= num;
            } else {
                k -= 1;
                curr *= 10;
            }
        }
        return curr;
    }

    private int getNum(int n, long first, long last) {
        int num = 0;

        while (first <= n) {
            num += Math.min(n + 1, last) - first;
            first *= 10;
            last *= 10;
        }
        return num;
    }
}
