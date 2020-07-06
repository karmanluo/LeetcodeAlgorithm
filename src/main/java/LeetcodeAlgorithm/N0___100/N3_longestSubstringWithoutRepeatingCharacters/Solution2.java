package LeetcodeAlgorithm.N0___100.N3_longestSubstringWithoutRepeatingCharacters;

import java.util.HashSet;

/*
 * Approach 2: Sliding Window
 * */
public class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        HashSet<Character> set = new HashSet<>();

        int i = 0, j = 0, ans = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j++;
                ans = Math.max(ans, j - i);
            } else {
                set.remove(s.charAt(i));
                i++;
            }
        }
        return ans;
    }
}
