package LeetcodeAlgorithm.N601_700.N696_CountBinarySubstrings;

public class Solution2 {
    public int countBinarySubstrings(String s) {
        int ans = 0, pre = 0, cur = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i - 1) != s.charAt(i)){
                ans += Math.min(pre, cur);
                pre = cur;
                cur = 1;
            }else {
                cur++;
            }
        }

        return ans + Math.min(pre, cur);
    }
}
