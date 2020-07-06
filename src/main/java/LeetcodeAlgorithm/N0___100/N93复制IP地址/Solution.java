package LeetcodeAlgorithm.N0___100.N93复制IP地址;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> restoreIpAddresses(String s) {
         List<String> list = new ArrayList<>();
         if (s.length() > 12) return list;

         dfs(s, list, "", 0, 0);
         return list;
    }

    private void dfs(String s, List<String> list, String res, int index, int sec) {
        if (sec == 4 && index == s.length()) {
            list.add(res);
            return;
        }
        if (sec > 4) return;

        for (int i = 1; i <= 3; i++) {
            if (i + index > s.length()) break;
            String section = s.substring(index, index + i);
            if (section.length() > 1 && section.startsWith("0") || Integer.parseInt(section) > 255) break;
            dfs(s, list, sec == 0 ? section : res + '.' + section , index + i, sec + 1);
        }
    }
}
