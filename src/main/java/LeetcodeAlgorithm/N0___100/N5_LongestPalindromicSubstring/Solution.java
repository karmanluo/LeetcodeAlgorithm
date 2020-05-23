//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
//
// Example 1:
//
//
//Input: "babad"
//Output: "bab"
//Note: "aba" is also a valid answer.
//
//
// Example 2:
//
//
//Input: "cbbd"
//Output: "bb"
//
//Approach 2: Brute Force
//The obvious brute force solution is to pick all possible starting and ending positions for a substring, and verify if it is a palindrome.
package LeetcodeAlgorithm.N0___100.N5_LongestPalindromicSubstring;

public class Solution {
    public static String longestPalindrome(String s) {
        String ans = "";
        int max = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {
                if (isPalindromic(s.substring(i, j)) && s.substring(i, j).length() > max) {
                    ans = s.substring(i, j);
                    max = ans.length();
                }
            }
        }
        return ans;
    }

    public static boolean isPalindromic(String s) {
        int len = s.length();
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("babad的最长回文为： " + longestPalindrome("babad"));
        System.out.println("cbbd的最长回文为： " + longestPalindrome("cbbd"));
    }
}
