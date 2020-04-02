package LeetcodeAlgorithm.N0___100.N5_LongestPalindromicSubstring;

/*
 * Approach 4: Expand Around Center
 *
 * In fact, we could solve it in O(n^2) time using only constant space
 * */
public class Solution3 {
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = (len1 > len2) ? len1 : len2;
            if(len > end - start + 1){
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    public static void main(String[] args) {
        System.out.println("babad的最长回文为： " + longestPalindrome("babad"));
        System.out.println("cbbd的最长回文为： " + longestPalindrome("cbbd"));
    }
}
