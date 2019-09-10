package LeetcodeAlgorithm.N11_ContainerWithMostWater;

import java.util.Scanner;

//Approach 2: Two Pointer Approach
public class Solution2 {
    public static int maxArea(int[] height) {
        int l = 0, r = height.length - 1;
        int max = 0;
        if (height.length < 2) return 0;
        while (l < r) {
            max = Math.max(max, Math.min(height[r], height[l]) * (r - l));
            if (height[l] > height[r]) r--;
            else{
                l++;
            }
        }
        return  max;
    }

    public static void main(String[] args) {
        System.out.println("请输入几个数并用逗号隔开：");
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String[] arr = str.split(",");
        int[] testArr = new int[arr.length];
        for (int i = 0; i < testArr.length; i++) {
            testArr[i] = Integer.parseInt(arr[i]);
        }
        System.out.println("最大的面积是： "+ maxArea(testArr));
    }
}
