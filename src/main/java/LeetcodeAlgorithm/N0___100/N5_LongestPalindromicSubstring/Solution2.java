/*
* Approach 2: Dynamic Programming
To improve over the brute force solution, we first observe how we can avoid unnecessary re-computation while validating
* palindromes. Consider the case "ababa". If we already knew that "bab" is a palindrome, it is obvious that "ababa" must
*  be a palindrome since the two left and right end letters are the same.
* */
package LeetcodeAlgorithm.N0___100.N5_LongestPalindromicSubstring;

public class Solution2 {
    public static String longestPalindrome(String s) {
        int len = s.length();

        if (len < 2){
            return s;
        }
        //dp[i][j]表示字串s[i,...,j]是否是回文
        boolean dp[][] = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        //先竖着填,再横着
        int maxLen = 1, startIndex = 0;
        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3 || dp[i + 1][j - 1]){
                        dp[i][j] = true;
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    startIndex = i;
                }
            }
        }

        return s.substring(startIndex, startIndex + maxLen);

    }

    public static void main(String[] args) {
        System.out.println("babad的最长回文为： " + longestPalindrome("babad"));
        System.out.println("cbbd的最长回文为： " + longestPalindrome("cbbd"));
    }
}
