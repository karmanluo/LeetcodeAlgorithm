package LeetcodeAlgorithm.N0___100.N64_MinimumPathSum;
/*
* 对solution1的优化，solution1的空间复杂度是o（mn），现在是在不改变grid[][]中值的情况下，将空间复杂度降低为o（m）
* */
public class Solution2 {
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] cur = new int[grid.length];
        //cur[i]表示j=0的时候，路径之和
        for (int i = 0; i < m; i++) {
            if(i == 0) cur[i] = grid[0][0];
            else
                cur[i] = cur[i - 1] + grid[i][0];
        }
        //根据j-1列的cur去更新j列的cur，更新到最终的结果就是最后一列的cur[m-1]
        for (int j = 1; j < n; j++) {
            cur[0] += grid[0][j];
            for (int i = 1; i < m; i++) {
                cur[i] = Math.min(cur[i - 1], cur[i]) + grid[i][j];
            }
        }
        return cur[m - 1];
    }
}
