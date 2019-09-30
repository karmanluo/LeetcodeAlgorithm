package LeetcodeAlgorithm.N62_UniquePaths;
/*
* 使用数学的方法，空间复杂度很低
* 1.总共的要走的步数为m-1 + n - 1 = m + n - 2;
* 2.里面总有n-1个方向
* */
public class Solution2 {
    public int uniquePaths(int m, int n) {
        int N = m + n - 2;
        int k = m - 1;
        long res = 1;

        for (int i = 1; i <= k; i++) {
            res = res * (N - k + i) / i;
        }
        return (int)res;
    }
}
