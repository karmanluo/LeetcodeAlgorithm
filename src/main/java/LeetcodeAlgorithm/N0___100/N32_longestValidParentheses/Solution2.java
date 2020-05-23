package LeetcodeAlgorithm.N0___100.N32_longestValidParentheses;
/*
* Approach 2: Using Dynamic Programming
* 用数组dp[]来存第i个位置结束的最长匹配。
*
* 解题思路：1.首先第i个位置是')'有效。
* 2.如果i-1个位置上是'('那么，我们第i个位置上的dp的值是dp[i] = dp[i-2] + 2 因为i-1和i构成一个匹配，所以需要加2，而在i-2
* 之前的位置也有可能是已经匹配的了，所以加dp[i-2]);
* 3.如果第i-1个位置也是‘)’，那么就要判断i-1的位置匹配的个数。首先跳过i-1位置匹配的括号个数
* 4.判断第i-dp[i-1]-1位置是否是'('，如果是左括号说明第i个位置是匹配的。此时的dp[i]=dp[i-1]+dp[i-dp[i-1]-2]+2
* 5.第i-dp[i-1]-1位置不是'('那么说明第i个位置的括号没有匹配上，所以dp[i]=0
* 举例：
*    0 1 2 3 4  5 6 7 8 9
*   （（（（ ）（ ））））
* dp:0 0 0 0 2  0 4 6 8 10
* 从前往后扫描一遍就可以解决：时间复杂度o（n） ，空间复杂度o（n）
*
* 单词：Parenthese 圆括号
* */
public class Solution2 {
    public int longestValidParentheses(String s){
        int[] dp = new int[s.length()];
        int max = 0;
        for (int i = 1; i < s.length(); i++) {//因为第一个位置肯定不匹配，所以跳过
            if (s.charAt(i)==')'){
                if (s.charAt(i-1) == '('){
                    dp[i] = (i > 2 ? dp[i -2] : 0) + 2;
                }else if(i - dp[i - 1] > 0 && s.charAt(i-dp[i-1]-1) == '('){//注意：这里的判断语句不要写反了，否则会报越界的错误
                    dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 > 0 ? dp[i - dp[i - 1] - 2]: 0) + 2;
                }
                max = (dp[i] > max ? dp[i] : max);
            }
        }
        return max;
    }
}
