package LeetcodeAlgorithm.N76_MinimumWindowSubstring;
//Given a string S and a string T, find the minimum window in S which will contain
// all the characters in T in complexity O(n).
//
// Example:
//Input: S = "ADOBECODEBANC", T = "ABC"
//Output: "BANC"
//
// Note:
// If there is no such window in S that covers all characters in T, return the
// empty string "".
// If there is such window, you are guaranteed that there will always be only
// one unique minimum window in S.
//
// Related Topics Hash Table Two Pointers String Sliding Window

import java.util.HashMap;

class Solution {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        //遍历字符串t，初始化每个字母的次数
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        int left = 0, right = 0;//左右指针形成一个窗口
        int ans_left = 0, ans_right = -1, min_len = Integer.MAX_VALUE;
        //遍历数组s
        while (right < s.length()) {
            char ch = s.charAt(right);
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) - 1);
                //判断 窗口中的字符串 和字符串t是否匹配
                while (match(map)){
                    int windowLen = right - left + 1;
                    if (windowLen < min_len){
                        ans_left = left;
                        ans_right = right;
                        min_len = windowLen;
                    }
                    //left_key 得到左指针字母
                    char left_key =  s.charAt(left);
                    if (map.containsKey(left_key)){
                        map.put(left_key, map.get(left_key) + 1);
                    }
                    left++;
                }
            }
            right++;
        }
        return s.substring(ans_left, ans_right + 1);
    }

    private boolean match(HashMap<Character, Integer> map){
        for (Integer value : map.values()){
            if (value > 0) {
                return false;
            }
        }
        return true;
    }
}

