package LeetcodeAlgorithm.N0___100.N84_LargestRectangleInHistogram;
//暴力法
class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        int len = heights.length;

        for (int i = 0; i < len; i++) {
            int h = heights[i], w = 1;
            int j = i;
            while (++j < len && heights[j] >= h) w++;
            j = i;
            while (--j >= 0 && heights[j] >= h) w++;
            maxArea = Math.max(maxArea, h * w);
        }

        return maxArea;
    }
}

