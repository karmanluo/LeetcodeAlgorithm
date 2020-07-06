package LeetcodeAlgorithm.N0___100.N69X的平方根;

public class Solution {
    public int mySqrt(int x) {
        if (x < 1) return x;
        int l = 1, r = x - 1;
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (mid > x / mid) r = mid;
            else if (mid < x / mid) {
                if (mid + 1 > x / (mid + 1)) return mid;
                l = mid + 1;
            } else {
                return mid;
            }
        }

        return l;
    }
}
