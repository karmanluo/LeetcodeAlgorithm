package LeetcodeAlgorithm.N62_UniquePaths;
/*
* 使用数学的方法，空间复杂度很低
* 1.总共的要走的步数为m-1 + n - 1 = m + n - 2;
* 2.里面总有n-1个方向
* */
public class Solution2 {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;// how much steps we need to do
        int k = m - 1;// number of steps that need to go down
        long res = 1;
        // here we calculate the total possible path number
        // Combination(N, k) = n! / (k!(n - k)!)
        // reduce the numerator and denominator and get
        // C = ( (n - k + 1) * (n - k + 2) * ... * n ) / k!
        for (int i = 1; i <= k; i++) {
            res = res * (N - k + i) / i;
        }
        return (int)res;
    }
}
