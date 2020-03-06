package LeetcodeAlgorithm.N154寻找重复数组中的最小值有重复;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/6 22:13
 */
public class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;

        while(lo < hi){
            int mid = lo + ((hi -lo) >>> 1);

            if(nums[mid] < nums[hi])    hi = mid;
            else if(nums[mid] > nums[hi])   lo = mid + 1;
            else    //this case is nums[mid] == nums[hi]
                hi--;
        }

        return nums[lo];
    }
}
