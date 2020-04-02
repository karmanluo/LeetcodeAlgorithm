package LeetcodeAlgorithm.N394字符串解码;

import java.util.Stack;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/7 13:39
 */
public class Solution {
    public String decodeString(String s) {
        Stack<Integer> inStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        StringBuilder curr = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()){
            if (Character.isDigit(ch)){
                k = k * 10 + ch - '0';
            }else if (ch == '['){
                inStack.push(k);
                strStack.push(curr);
                curr = new StringBuilder();
                k = 0;
            }else if (ch == ']'){
                StringBuilder tmp = curr;
                curr = strStack.pop();
                for (k = inStack.pop(); k > 0; k--) curr.append(tmp);
            }else curr.append(ch);
        }

        return curr.toString();
    }
}
