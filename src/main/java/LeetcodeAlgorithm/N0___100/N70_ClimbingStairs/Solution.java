package LeetcodeAlgorithm.N0___100.N70_ClimbingStairs;
//You are climbing a stair case. It takes n steps to reach to the top.
//
// Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
// Note: Given n will be a positive integer.
//
// Example 1:
//Input: 2
//Output: 2
//Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//
//
// Example 2:
//Input: 3
//Output: 3
//Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step
//
// Related Topics Dynamic Programming
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n+1];

        for(int i = 0; i <= n; i++){
            if(i == 1 || i == 0) dp[i] = 1;
            else
                dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
