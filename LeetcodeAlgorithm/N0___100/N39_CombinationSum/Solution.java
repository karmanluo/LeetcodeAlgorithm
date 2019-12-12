package LeetcodeAlgorithm.N39_CombinationSum;
//Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.
//
// The same repeated number may be chosen from candidates unlimited number of times.
//
// Note:
// All numbers (including target) will be positive integers.
// The solution set must not contain duplicate combinations.
//
// Example 1:
//Input: candidates = [2,3,6,7], target = 7,
//A solution set is:
//[
//  [7],
//  [2,2,3]
//]
//
//
// Example 2:
//Input: candidates = [2,3,5], target = 8,
//A solution set is:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//]
// Related Topics Array Backtracking

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 解题思路：
 * 使用backtracking
 * A general approach to backtracking questions in Java
 * (Subsets, Permutations, Combination Sum, Palindrome Partitioning)
 *
 * 首先传入nums， target，然后不断的迭代，直到相等的时候代表结束
 * */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(list, new ArrayList<>(), candidates, target, 0);
        return list;
    }

    public void backtrack(List<List<Integer>> list, List<Integer> templeList, int[] nums, int remain, int start) {
        if (remain < 0) return;
        else if (remain == 0) {
            list.add(new ArrayList<>(templeList));
        } else {
            for (int i = start; i < nums.length; i++){
                templeList.add(nums[i]);
                backtrack(list, templeList, nums, remain - nums[i], i);//不是i+1是为了保证nums[i]可以被多次使用
                templeList.remove(templeList.size() -1 );
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
