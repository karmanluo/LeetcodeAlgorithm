package LeetcodeAlgorithm.N64_MinimumPathSum;
//Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all
// numbers along its path.
//
// Note: You can only move either down or right at any point in time.
//
// Example:
//
//
//Input:
//[
//  [1,3,1],
//  [1,5,1],
//  [4,2,1]
//]
//Output: 7
//Explanation: Because the path 1→3→1→1→1 minimizes the sum.
//
// Related Topics Array Dynamic Programming
/*
* This is a typical DP problem. Suppose the minimum path sum of arriving at point (i, j) is S[i][j], then the state
* equation is S[i][j] = min(S[i - 1][j], S[i][j - 1]) + grid[i][j].
*
* Well, some boundary conditions need to be handled. The boundary conditions happen on the topmost row (S[i - 1][j] does
* not exist) and the leftmost column (S[i][j - 1] does not exist). Suppose grid is like [1, 1, 1, 1], then the minimum sum
* to arrive at each point is simply an accumulation of previous points and the result is [1, 2, 3, 4].
*
* Now we can write down the following (unoptimized) code.
* */
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] sum = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++){
                if (i == 0 && j == 0)
                    sum[i][j] = grid[0][0];
                else if (i == 0)
                    sum[0][j] = sum[0][j - 1] + grid[0][j];
                else if (j == 0)
                    sum[i][0] = sum[i - 1][0] + grid[i][0];
                else
                    sum[i][j] = Math.min(sum[i - 1][j], sum[i][j - 1]) + grid[i][j];
            }
        }
        return sum[m-1][n-1];
    }
}

