package LeetcodeAlgorithm.N0___100.N84_LargestRectangleInHistogram;

import java.util.Stack;
/*
* 使用栈的方式，思路比较奇特，不太容易想到
* */
public class Solution2 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        for (int i = 0; i <= len; i++) {
            int h = (i == len ? 0 : heights[i]);
            if (s.empty() || h >= heights[s.peek()]){
                s.push(i);
            }else {
                int top = s.pop();
                maxArea = Math.max(maxArea, heights[top] * (s.empty() ? i : (i - 1 - s.peek())));
                i--;
            }
        }
        return maxArea;
    }
}
