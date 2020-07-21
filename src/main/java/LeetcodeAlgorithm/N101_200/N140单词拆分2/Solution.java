package LeetcodeAlgorithm.N101_200.N140单词拆分2;

import java.util.*;

public class Solution {

    int maxLen = 0, minLen = Integer.MAX_VALUE;

    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> hs = new HashSet<>(wordDict);
        for (String w : wordDict) {
            maxLen = Math.max(maxLen, w.length());
            minLen = Math.min(minLen, w.length());
        }
        Map<Integer, List<String>> map = new HashMap<>();
        return DFS(hs, s, 0, map);
    }

    private List<String> DFS(Set<String> hs, String s, int start, Map<Integer, List<String>> map) {
        if (map.containsKey(start)) return map.get(start);
        List<String> list = new ArrayList<>();
        if (start == s.length()) list.add("");

        for (int end = start + minLen; end <= start + maxLen && end <= s.length(); end++) {
            if (hs.contains(s.substring(start, end))) {
                List<String> nexts = DFS(hs, s, end, map);
                for (String next : nexts) {
                    if (next == "") list.add(s.substring(start, end) + next);
                    else list.add(s.substring(start, end) + " " + next);
                }
            }
            map.put(start, list);
        }
        return list;
    }

}
