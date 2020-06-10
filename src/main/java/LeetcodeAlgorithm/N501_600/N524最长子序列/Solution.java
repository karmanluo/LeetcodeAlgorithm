package LeetcodeAlgorithm.N501_600.N524最长子序列;

import java.util.List;

public class Solution {
    public String findLongestWord(String s, List<String> d) {
        String longestString = "";
        for (String target : d) {
            int l1 = longestString.length(), l2 = target.length();
            if (l1 > l2 || (l1 == l2 && longestString.compareTo(target) < 0)) continue;
            if (isSubStr(s, target)) {
                longestString = target;
            }
        }

        return longestString;
    }

    private boolean isSubStr(String s, String target) {
        int i = 0, j = 0;
        while (i < s.length() && j < target.length()) {
            if (s.charAt(i) == target.charAt(j)) j++;
            i++;
        }

        return j == target.length();
    }
}
