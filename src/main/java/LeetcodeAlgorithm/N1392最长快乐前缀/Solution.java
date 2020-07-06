package LeetcodeAlgorithm.N1392最长快乐前缀;

public class Solution {
    public String longestPrefix(String s) {
        int[] lps = new int[s.length()];
        for (int j = 0, i = 1; i < s.length(); i++) {
            while (j > 0 && s.charAt(i) != s.charAt(j)) j = lps[j - 1];
            if (s.charAt(i) == s.charAt(j))   lps[i] = ++j;
        }
        return s.substring(0, lps[s.length() - 1]);
    }
}
