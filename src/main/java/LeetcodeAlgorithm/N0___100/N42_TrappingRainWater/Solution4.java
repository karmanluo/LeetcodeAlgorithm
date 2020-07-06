package LeetcodeAlgorithm.N0___100.N42_TrappingRainWater;

import java.util.ArrayDeque;
import java.util.Deque;

//单调栈解法
public class Solution4 {
    public int trap(int[] height) {
        int len = height.length;
        int res = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int pop = stack.pop();
                int hi = height[pop];
                if (stack.isEmpty()) break;
                int waterWidth = i - stack.peek() - 1;
                int waterHi = Math.min(height[i], height[stack.peek()]) - hi;

                res += waterHi * waterWidth;
            }
            stack.push(i);
        }

        return res;
    }
}
