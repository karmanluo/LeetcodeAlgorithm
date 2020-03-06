package LeetcodeAlgorithm.N0___100.N74搜索二维矩阵;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/6 12:12
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int rows = matrix.length, cols = matrix[0].length;
        int start = 0, end = rows * cols - 1;

        while (start <= end) {
            int mid = start + ((end - start) >>> 1);

            if (target == matrix[mid / cols][mid % cols]) return true;
            else if (target < matrix[mid / cols][mid % cols]) end = mid - 1;
            else start = mid + 1;
        }

        return false;
    }
}
