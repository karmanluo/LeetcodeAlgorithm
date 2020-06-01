package LeetcodeAlgorithm.N101_200.N167两数之和有序的数组;

public class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int[] r = new int[2];
        if (numbers == null || numbers.length < 2)
            return r;
        int left = 0, right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                r[0] = left + 1;
                r[1] = right + 1;
                break;
            } else if (sum > target) right--;
            else left++;
        }

        return r;
    }
}
