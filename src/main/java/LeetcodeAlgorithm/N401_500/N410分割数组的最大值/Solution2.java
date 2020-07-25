package LeetcodeAlgorithm.N401_500.N410分割数组的最大值;

public class Solution2 {
    public int splitArray(int[] nums, int m) {
        int n = nums.length;
        int left = 0, right = 0;
        for (int i = 0; i < n; i++) {
            right += nums[i];
            if (left < nums[i]) left = nums[i];
        }

        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (check(nums, mid, m)) right = mid;
            else left = mid + 1;
        }

        return left;
    }

    private boolean check(int[] nums, int target, int m) {
        int sum = 0;
        int cnt = 1;
        for (int i = 0; i < nums.length; i++) {
            if (sum + nums[i] > target) {
                cnt++;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }

        return cnt <= m;
    }
}
