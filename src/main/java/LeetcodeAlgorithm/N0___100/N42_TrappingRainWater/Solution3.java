package LeetcodeAlgorithm.N0___100.N42_TrappingRainWater;

/*
* 解法二 解题思路回顾：
 * 1.max_left[i]、max_right[i]分别代表第 i 列左边和右边最高的墙的高度
 * 2.max_left[i] = Max(max_left[i-1],height[i-1])、max_right[i] = Max(max_right[i+1],height[i+1])
 * 3.扫描求水量之和
 * 优化思路：空间复杂度为o（n） ，尝试用左右指针的方式来优化
 *
* 解题思路：
* 因为每个位置的水量都是查看左右max中小的那一个，所以在最开始的时候进行一次比较可以确定在哪一边去更新
*
* 时间复杂度：o（n） 空间复杂度o（1）
* */
public class Solution3 {
    public int trap(int[] height) {
        int res = 0, max_left = 0, max_right = 0, left = 0, right = height.length - 1;
        while(left < right){
            //最开始的时候进行一次比较可以确定在哪一边去更新，左边更小，去左边更新判断
            if (height[left] < height[right]){//水量最多和max_left一样多
                if (height[left] >= max_left)
                     max_left = height[left];
                else
                    res += max_left - height[left];
                left++;
            }
            //右边更小，去右边判断
            else {
                if (height[right] >= max_right)
                    max_right = height[right];
                else
                    res += max_right - height[right];
                right--;
            }
        }
        return res;
    }
}
