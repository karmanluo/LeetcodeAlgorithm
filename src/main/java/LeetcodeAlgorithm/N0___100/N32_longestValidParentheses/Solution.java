package LeetcodeAlgorithm.N0___100.N32_longestValidParentheses;

class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int max = 0;
        int count;
        for (int i = 0; i < n; i++) {
            count = 0;
            for (int j = i; j < n; j++) {
                if (s.charAt(j) == '(') {
                    count++;
                }
                if (s.charAt(j) == ')') {
                    count--;
                }
                //count < 0 说明右括号多了，此时无论后边是什么，一定是非法字符串了，所以可以提前结束循环
                if (count < 0) {
                    break;
                }
                if (count == 0) {
                    if (j - i + 1 > max) {
                        max = j - i + 1;
                    }
                }
            }
        }
        return max;
    }
}
