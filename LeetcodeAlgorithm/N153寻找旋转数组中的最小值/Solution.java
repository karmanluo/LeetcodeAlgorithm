package LeetcodeAlgorithm.N153寻找旋转数组中的最小值;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/6 21:27
 */
public class Solution {
    public int findMin(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high){
            int mid = low + ((high - low) >>> 1);

            if (nums[mid] < nums[high]) high = mid;
            else low = mid + 1;
        }

        return nums[low];
    }
}
