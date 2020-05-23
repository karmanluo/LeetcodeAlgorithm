package LeetcodeAlgorithm.N0___100.N32_longestValidParentheses;

import java.util.Stack;

/*Using Stack
*
* 解题思路:
* 1.将-1入栈，因为在计算的时候需要减去栈底方便计算
* 2.遇到'('将对应的index进栈
* 3.遇到')'将栈中元素出栈
* 4.如果出栈后栈为空，将‘）’元素的index入栈，这里的index和上面最开始的-1一样都是作为计算长度的一个凭证
* 5.如果出栈后不为空，计算一次max结果，max = i - stack.peek();
*
* 分析：时间复杂度o（n） 空间复杂度o（n）
* */
public class Solution3 {
    public int longestValidParentheses(String s){
        int maxLVP = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                stack.push(i);
            }else{
                stack.pop();
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    maxLVP =  (i - stack.peek() > maxLVP ? i - stack.peek() : maxLVP);
                }
            }
        }
        return maxLVP;
    }
}
