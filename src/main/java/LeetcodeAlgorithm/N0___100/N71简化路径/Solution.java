package LeetcodeAlgorithm.N0___100.N71简化路径;

public class Solution {
    public String simplifyPath(String path) {
        StringBuilder res = new StringBuilder("/");
        path += "/";

        StringBuilder sb = new StringBuilder();
        for (char ch : path.toCharArray()) {
            if (ch != '/') {
                sb.append(ch);
            } else {
                if (sb.toString().equals("..")) {
                    if (res.length() > 1) {
                        res.deleteCharAt(res.length() - 1);
                        while (res.charAt(res.length() - 1) != '/') res.deleteCharAt(res.length() - 1);
                    }
                }
                else if (!sb.toString().equals("") && !sb.toString().equals(".")) res.append(sb).append('/');
                sb = new StringBuilder();
            }
        }

        if (res.length() > 1) res.deleteCharAt(res.length() - 1);
        return res.toString();
    }
}
