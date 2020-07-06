package LeetcodeAlgorithm.N0___100.N85_MaximalRectangle;

import java.util.ArrayDeque;
import java.util.Deque;

//Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.
//
// Example:
//Input:
//[
//  ["1","0","1","0","0"],
//  ["1","0","1","1","1"],
//  ["1","1","1","1","1"],
//  ["1","0","0","1","0"]
//]
//Output: 6
//
/*
 * Suppose there is a 2D matrix like
 * 1 1 0 1 0 1
 * 0 1 0 0 1 1
 * 1 1 1 1 0 1
 * 1 1 1 1 0 1
 * ①First initiate the height array as 1 1 0 1 0 1, which is just a copy of the first row.
 * ①Then we can easily calculate the max area is 2.
 * ②Then update the array. We scan the second row, when the matrix[1][i] is 0, set the height[i] to 0; else height[i] += 1,
 * ② which means the height has increased by 1. So the height array again becomes 0 2 0 0 1 2. The max area now is also 2.
 * ③Apply the same method until we scan the whole matrix. the last height arrays is 2 4 2 2 0 4,
 * ③   so the max area has been found as 2 * 4 = 8.
 * */
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int maxArea = 0;
        int m = matrix.length, n = matrix[0].length;
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == '1') heights[i] = 1;
        }
        maxArea = maxRectangleArea(heights);
        for (int i = 1; i < m; i++) {
            updateHeights(matrix, heights, i);
            maxArea = Math.max(maxArea, maxRectangleArea(heights));
        }

        return maxArea;
    }

    public void updateHeights(char[][] matrix, int[] heights, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] == '1') heights[i] += 1;
            else heights[i] = 0;
        }
    }

    public int maxRectangleArea(int[] heights) {
        int len = heights.length;
        int[] arrH = new int[len + 2];
        Deque<Integer> stack = new ArrayDeque<>();
        //               src srcPos dest destPos len
        System.arraycopy(heights, 0, arrH, 1, len);
        int res = 0;
        for (int i = 0; i < arrH.length; i++) {
            while (!stack.isEmpty() && arrH[i] < arrH[stack.peek()]) {
                int h = arrH[stack.pop()];
                int w = i - stack.peek() - 1;
                res = Math.max(res, h * w);
            }
            stack.push(i);
        }
        return res;
    }
}
