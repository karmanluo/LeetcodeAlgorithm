/*
* Approach 2: Dynamic Programming
To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating
* palindromes. Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must
*  be a palindrome since the two left and right end letters are the same.
* */
package LeetcodeAlgorithm.N5_LongestPalindromicSubstring;

public class Solution2 {
    public static String longestPalindrome(String s) {
        String res = "";
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for (int i = n - 1; i >= 0 ; i--) {
            for (int j = i; j < n; j++) {
                dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i+1][j-1]);
                if(dp[i][j] && j - i + 1 > res.length()){
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("babad的最长回文为： " + longestPalindrome("babad"));
        System.out.println("cbbd的最长回文为： " + longestPalindrome("cbbd"));
    }
}
