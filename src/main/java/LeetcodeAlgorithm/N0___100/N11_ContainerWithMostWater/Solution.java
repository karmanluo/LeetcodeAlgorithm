//Given n non-negative integers a1, a2, ..., an , where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.
//
// Note: You may not slant the container and n is at least 2.

// The above vertical lines are represented by array [1,8,6,2,5,4,8,3,7]. In this case, the max area of water (blue section) the container can contain is 49.
//
// Example:
//
//Input: [1,8,6,2,5,4,8,3,7]
//Output: 49
//
package LeetcodeAlgorithm.N0___100.N11_ContainerWithMostWater;

import java.util.Scanner;

//Approach 1: Brute Force
public class Solution {
    public static int maxArea(int[] height) {
        int n = height.length;
        int max = 0;
        if (n<2){
            return 0;
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(Math.min(height[i],height[j]) * (j - i), max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("请输入几个数并用逗号隔开：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr  = str.split(",");
        int[] b = new int[arr.length];
        for(int j = 0; j<b.length;j++) {
            b[j] = Integer.parseInt(arr[j]);
            System.out.print(b[j]+" ");
        }
        System.out.println("最大的面积是： "+ maxArea(b));
    }

}
