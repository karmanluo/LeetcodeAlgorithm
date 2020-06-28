package LeetcodeAlgorithm.N0___100.N84_LargestRectangleInHistogram;

import java.util.ArrayDeque;
import java.util.Deque;

//单调栈 解法
public class Solution2 {
    public int largestRectangleArea(int[] heights) {
        int len = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        int[] newHeights = new int[len + 2];
        System.arraycopy(heights, 0, newHeights, 1, len);

        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int h = newHeights[stack.pop()];
                int w = i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
