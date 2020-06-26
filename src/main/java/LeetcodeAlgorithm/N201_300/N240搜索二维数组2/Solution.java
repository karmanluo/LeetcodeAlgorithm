package LeetcodeAlgorithm.N201_300.N240搜索二维数组2;

public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;

        int m = matrix.length - 1, n = 0;
        while (m >= 0 && n <= matrix[0].length - 1) {
            if (target == matrix[m][n]) return true;
            else if (target > matrix[m][n]) n++;
            else m--;
        }

        return false;
    }
}
