package LeetcodeAlgorithm.N0___100.N10_RegularExpressionMatching;

/*
 * 使用的是动态规划的思想
 * 参考链接
 *https://leetcode.wang/leetCode-10-Regular-Expression-Matching.html
 * */
public class Solution2 {
    public static boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length(); j >= 0; j--) {
                if (i == s.length() && j == p.length())
                    continue;
                boolean firstMatch = (i < s.length() && j < p.length()) &&
                        (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
                if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
                    dp[i][j] = (dp[i][j + 2]) || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        System.out.println("String s = abc, String p = .a*bc. isMatch ? -> " + isMatch("abc", ".a*bc"));
    }

}
