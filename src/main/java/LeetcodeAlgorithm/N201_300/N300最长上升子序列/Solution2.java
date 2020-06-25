package LeetcodeAlgorithm.N201_300.N300最长上升子序列;

public class Solution2 {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] arr = new int[nums.length];
        int nextInsertPosOfArr = 0;  // also equals  with arr.length

        for (int num : nums) {
            int lo = 0, hi = nextInsertPosOfArr;

            while (lo < hi) {
                int mid = lo + ((hi - lo) >>> 1);
                if (arr[mid] < num) lo = mid + 1;
                else hi = mid;
            }
            //lo 代表应该插入的位置，并且是强制覆盖的；只有当lo的位置和nextInsertPosOfArr的位置相同时才更新nextInsertPosOfArr。
            arr[lo] = num;
            if (lo == nextInsertPosOfArr) nextInsertPosOfArr++;
        }

        return nextInsertPosOfArr;
    }
}
