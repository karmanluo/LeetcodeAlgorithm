package 剑指offer.N62圆圈中最后剩下的数字;

class Solution {
    public int lastRemaining(int n, int m) {
        if (n == 1) return 0;

        return (m + lastRemaining(n - 1, m)) % n;
    }
}
