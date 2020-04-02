package LeetcodeAlgorithm.N0___100.N32_longestValidParentheses;
/*
* Approach 4: Without extra space 空间复杂度仅为o(1)，时间复杂度o（n）
*
* 解题思路：仅用left和right两个变量
* 1.从左边开始扫描，扫描到'('时候left++；   扫到‘)’right++， 扫到right的时候计数一次；
* 当left == right的时候计算一次有效长度，并得到当前最长
* 2.如果right比left大，设置left和right为0
* 3.从右扫描一次这个串（步骤为1和2）
* */
public class Solution4 {
    public int longestValidParentheses(String s){
        int maxLVP = 0, left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }
            if (left == right){
                maxLVP = (right * 2 > maxLVP ?  right * 2 : maxLVP);
            }else if (right > left){
                right = left = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() -1 ; i >= 0; i--) {
            if (s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }
            if (left == right){
                maxLVP = (right * 2 > maxLVP ?  right * 2 : maxLVP);
            }else if (right < left){
                right = left = 0;
            }
        }
        return maxLVP;
    }
}
