package LeetcodeAlgorithm.N42_TrappingRainWater;

/*
 * 解法一回顾：
 * .从左往右进行扫描（第一个位置和最后一个位置不可能有水）
 * 2.扫描到的位置，求出左边最高柱left_max和右边最高柱right_max
 * 3.左右left_max、right_max求出最小的min.
 * 4.当前位置的水量为min-height[i]
 * 5.计算水量之和
 *结论：对于每一列，我们求它左边最高的墙和右边最高的墙，都是重新遍历一遍所有高度，这里我们可以优化一下
 *
 * 解法二 解题思路：
 * 1.max_left[i]、 max_right[i]分别代表第 i 列左边和右边最高的墙的高度
 * 2.max_left[i] = Max(max_left[i-1],height[i-1]) 、max_right[i] = Max(max_right[i+1] , height[i+1])
 * 3.扫描求水量之和
 *
 * 时间复杂度：三次扫描 3*n，所以是 o（n）          空间复杂度：o(n)
 * */
public class Solution2 {
    public int trap(int[] height) {
        int res = 0;
        int[] left_max = new int[height.length];
        int[] right_max = new int[height.length];
        //每个位置，确定左边最大
        for (int i = 1; i < height.length - 1; i++) {
            left_max[i] = Math.max(left_max[i - 1], height[i - 1]);
        }
        //每个位置，确定右边最大
        for (int i = height.length - 2; i > 0; i--) {
            right_max[i] = Math.max(right_max[i + 1] ,height[i + 1]);
        }
        //计算水量和
        for (int i = 1; i < height.length - 1; i++) {
            int min = Math.min(left_max[i], right_max[i]);
            if (min > height[i]) res += min - height[i];
        }
        return res;
    }
}
