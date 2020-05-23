package LeetcodeAlgorithm.N0___100.N85_MaximalRectangle;

import java.util.Stack;

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

        int[] height = new int[matrix[0].length];
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i] == '1')
                height[i] = 1;
        }
        int result = largestInline(height);
        for (int row = 1; row < matrix.length; row++) {
            updateHeight(matrix, height, row);
            result = Math.max(result, largestInline(height));
        }
        return result;
    }

    private void updateHeight(char[][] matrix, int[] height, int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] == '1')  height[i] += 1;
            else height[i] = 0;
        }
    }

    private int largestInline(int[] height) {
        int len = height.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : height[i]);
            if (s.empty() || h >= height[s.peek()]){
                s.push(i);
            }else {
                int top = s.pop();
                maxArea = Math.max(maxArea, height[top] * (s.empty() ? i : (i - 1 - s.peek())));
                i--;
            }
        }
        return maxArea;
    }
}
