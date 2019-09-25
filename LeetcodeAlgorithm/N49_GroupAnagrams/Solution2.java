package LeetcodeAlgorithm.N49_GroupAnagrams;

import java.util.*;

//Given an array of strings, group anagrams 颠倒 together.
//
// Example:
//Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
//Output:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//]
// Note:
// All inputs will be in lowercase.
// The order of your output does not matter.
// Related Topics Hash Table String
/*
 * 解题思路：字符串中出现的字母次数是一样的时候视为同一个串
 *
 * 1.遍历整个字符串数组
 * 2.对每个串进行个数统计，统计的结果转化为字符串作为key
 * 3.在2中一样的序列，对应的string存进同一个List
 *
 * 假设 N 为strs的长度，str[i]的长度为 K
 * 时间复杂度分析：O(NK)
 * 空间复杂度分析：O(NK)
 * */
public class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return new ArrayList<>();
        int[] count = new int[26];
        Map<String, List<String>> ans = new HashMap<>();
        for (String s: strs) {
            Arrays.fill(count, 0); //将count里面都赋值为0
            for (char c : s.toCharArray()) count[c - 'a']++;
            //避免追加字符串时候频繁开辟内存空间
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                //sb.append("#");//也可不要
                sb.append(count[i]);
            }
            String key = sb.toString();
            if (!ans.containsKey(key)) ans.put(key, new ArrayList<>());
            ans.get(key).add(s);
        }
        return new ArrayList<>(ans.values());
    }
}
