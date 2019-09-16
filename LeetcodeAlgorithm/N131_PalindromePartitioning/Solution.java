package LeetcodeAlgorithm.N131_PalindromePartitioning;

import java.util.ArrayList;
import java.util.List;

//Given a string s, partition s such that every substring of the partition is a palindrome(回文).
// Return all possible palindrome partitioning of s.
//
// Example:
//Input: "aab"
//Output:
//[
//  ["aa","b"],
//  ["a","a","b"]
//]
// Related Topics Backtracking
//leetcode submit region begin(Prohibit modification and deletion)
/*
 * 理解题意：将字符串s拆分，拆分为回文子串。
 * 首先：最简单的形式就是每一个字串都是单个字母的情况。
 *
 * 方法一：回溯法
 * */
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    public void backtrack(List<List<String>> list, List<String> tempList, String s, int start) {
        if (start == s.length()) {
            list.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }
    public boolean isPalindrome(String s, int low, int high) {
        while (low < high)
            if (s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
