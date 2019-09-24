package LeetcodeAlgorithm.N42_TrappingRainWater;

//Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water
// it is able to trap after raining.
//The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water
// (blue section) are being trapped.
//
// Example:
//Input: [0,1,0,2,1,0,1,3,2,1,2,1]
//Output: 6
// Related Topics Array Two Pointers Stack
/*
* 解题思路：从左到右按列求水量
* 1.从左往右进行扫描（第一个位置和最后一个位置不可能有水）
* 2.扫描到的位置，求出左边最高柱left_max和右边最高柱right_max
* 3.左右left_max、right_max求出最小的min.
* 4.当前位置的水量为min-height[i]
* 5.计算水量之和
*
* 时间复杂度：o(n^2) 空间复杂度o(1)
* */

class Solution {
    public int trap(int[] height) {
        int res = 0;
        //从左往右进行扫描（第一个位置和最后一个位置不可能有水）
        for (int i = 1; i < height.length - 1; i++) {
            //求出左边最高柱left_max
            int left_max = 0;
            for (int j = i - 1; j >= 0 ; j--) {
                if (height[j] > left_max)   left_max = height[j];
            }
            //右边最高柱right_max
            int right_max = 0;
            for (int j = i + 1; j < height.length; j++) {
                if (height[j] > right_max)  right_max = height[j];
            }
            //左右left_max、right_max求出最小的min.
            int min = left_max < right_max ? left_max : right_max;
            if (min > height[i]) res += min - height[i];
        }
        return res;
    }
}

