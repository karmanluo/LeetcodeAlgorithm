package LeetcodeAlgorithm.N3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;

/*
 * Approach 3: Sliding Window Optimized
 * */
public class Solution3 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println("abcabcbb最长字串的长度结果为：" + lengthOfLongestSubstring("abcabcbb"));
        System.out.println("bbbbb最长字串的长度结果为：" + lengthOfLongestSubstring("bbbbb"));
        System.out.println("pwwkew最长字串的长度结果为：" + lengthOfLongestSubstring("pwwkew"));
        System.out.println("abcadd：" + lengthOfLongestSubstring("abcadd"));
    }
}
