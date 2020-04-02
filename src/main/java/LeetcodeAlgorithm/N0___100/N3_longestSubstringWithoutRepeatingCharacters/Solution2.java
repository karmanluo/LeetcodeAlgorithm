package LeetcodeAlgorithm.N0___100.N3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/*
* Approach 2: Sliding Window
* */
public class Solution2 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();
        int i = 0, j = 0, ans = 0;
        while (i<n && j < n){
            // try to extend the range [i, j]
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j-i);
            }else {
                set.remove(s.charAt(i++));
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        System.out.println("abcabcbb最长字串的长度结果为："+ lengthOfLongestSubstring("abcabcbb"));
        System.out.println("bbbbb最长字串的长度结果为："+ lengthOfLongestSubstring("bbbbb"));
        System.out.println("pwwkew最长字串的长度结果为："+ lengthOfLongestSubstring("pwwkew"));
    }
}
