package LeetcodeAlgorithm.N0___100.N78_Subsets;

import java.util.ArrayList;
import java.util.List;

/*
* Given a set of distinct不同的 integers, nums, return all possible subsets (the power set).
* Note: The solution set must not contain duplicate subsets.
* 可以利用位运算的操作来做
* 子集个数：2^n，因为每个位置分为选和不选的两种可能性。
* */
public class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        int totalNum = 1<< nums.length;
        List<List<Integer>> res = new ArrayList<>(totalNum);

        for (int i = 0; i < totalNum; i++) {//取值的可能性 000， 001， 010，...，111；如果集合有三个元素，就是8种
            List<Integer> tempList = new ArrayList<>();
            for (int j = 0; j < nums.length; j++) {//前面给出的000、001这些数，和这些数的每一位去比较，有1就加
                if ((i & 1<<j) != 0){//    “<< ” 大于 “！=” 大于 “&与”
                    tempList.add(nums[j]);
                }
            }
            res.add(new ArrayList<>(tempList));
        }
        return res;
    }
}
