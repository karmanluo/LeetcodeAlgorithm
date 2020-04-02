package LeetcodeAlgorithm.N0___100.N49_GroupAnagrams;

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


import java.util.*;

/*
* 解题思路：排序后，字符串仍然是一样的
* 1.遍历整个字符串数组
* 2.每一个字符串排一个序
* 3.在2中一样的序列，对应的string存进同一个List
*  假设 N 为strs的长度，str[i]的长度为 K
 * 时间复杂度分析：O(NKlogK)    排序的时间复杂度为KlogK
 * 空间复杂度分析：O(NK)
* */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        for (String s : strs) {
            char[] ch = s.toCharArray();
            Arrays.sort(ch);
            String key = String.valueOf(ch);
            if (!res.containsKey(key))  res.put(key, new ArrayList<>());
            res.get(key).add(s);
        }
        return new ArrayList<>(res.values());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
