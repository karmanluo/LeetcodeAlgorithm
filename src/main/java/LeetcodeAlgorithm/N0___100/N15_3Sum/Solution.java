package LeetcodeAlgorithm.N0___100.N15_3Sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

//Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
// Note:
//
// The solution set must not contain duplicate triplets.
//
// Example:
//
//
//Given array nums = [-1, 0, 1, 2, -1, -4],
//
//A solution set is:
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
//
//
public class Solution {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int len = nums.length;
        if (len < 3) return res;

        Arrays.sort(nums);

        for (int k = 0; k < len - 2 && nums[k] <= 0; k++) {
            if(k > 0 && nums[k - 1] == nums[k]) continue;
            int target = -nums[k];
            int i = k + 1, j = len - 1;
            while (i < j) {
                if (nums[i] + nums[j] == target) {
                    res.add(Arrays.asList(nums[k], nums[i], nums[j]));
                    while (i < j && nums[i + 1] == nums[i]) i++;
                    while (i < j && nums[j - 1] == nums[j]) j--;
                    i++; j--;
                }
                else if (nums[i] + nums[j] > target) j--;
                else i++;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println("请输入数组");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String[] arr = str.split(",");
        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            nums[i] = Integer.parseInt(arr[i]);
        }
        List<List<Integer>> lists = threeSum(nums);
        System.out.println("3sum的结果是：" + lists);

    }
}
