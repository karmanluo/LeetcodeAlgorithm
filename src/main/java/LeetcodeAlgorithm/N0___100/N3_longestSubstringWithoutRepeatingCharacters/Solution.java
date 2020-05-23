package LeetcodeAlgorithm.N0___100.N3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/*
* Given a string, find the length of the longest substring without repeating characters.

Example 1:

Input: "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.
Example 2:

Input: "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.
Example 3:

Input: "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
             Note that the answer must be a substring, "pwke" is a subsequence and not a substring.*/
public class Solution {
    /*
    * Approach 1: Brute Force(时间复杂度o(n^3))
    *
    * */
    public static int lengthOfLongestSubstring(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n + 1; j++) {
                if(allUnique(s, i, j)){
                    ans = Math.max(ans, j-i);
                }
            }
        }
        return ans;
    }
    public static boolean allUnique(String s, int start, int end) {
        HashSet<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if(set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println("abcabcbb最长字串的长度结果为："+ lengthOfLongestSubstring("abcabcbb"));
        System.out.println("bbbbb最长字串的长度结果为："+ lengthOfLongestSubstring("bbbbb"));
        System.out.println("pwwkew最长字串的长度结果为："+ lengthOfLongestSubstring("pwwkew"));
    }
}
