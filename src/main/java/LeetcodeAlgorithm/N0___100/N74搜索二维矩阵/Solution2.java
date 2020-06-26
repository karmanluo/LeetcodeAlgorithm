package LeetcodeAlgorithm.N0___100.N74搜索二维矩阵;

public class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int lo = 0, hi = m * n - 1;

        while (lo < hi) {
            int mid = lo + ((hi - lo) >> 1);
            if (target > matrix[mid / n][mid % n]) lo = mid + 1;
            else hi = mid;
        }
        return target == matrix[lo / n][lo % n] ? true : false;
    }
}
