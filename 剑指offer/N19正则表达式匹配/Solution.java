package 剑指offer.N19正则表达式匹配;

public class Solution {

    //很难想到，直接背
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean isFirstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && (p.charAt(1) == '*')){
            return isMatch(s, p.substring(2)) || (isFirstMatch && isMatch(s.substring(1), p));
        }
        return isFirstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
