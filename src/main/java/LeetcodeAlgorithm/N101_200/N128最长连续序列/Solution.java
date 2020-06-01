package LeetcodeAlgorithm.N101_200.N128最长连续序列;

import java.util.HashMap;

public class Solution {
    public int longestConsecutive(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int num : nums){
            if (!map.containsKey(num)){
                int left = (map.containsKey(num - 1)) ? map.get(num - 1) : 0;
                int right = (map.containsKey(num + 1)) ? map.get(num + 1) : 0;
                //sum表示num所在序列的长度
                int sum = left + right + 1;
                map.put(num, sum);

                res = Math.max(res, sum);

                //将num数所对应的边界值的  长度  也进行更新
                //如果没有左右边界，不会产生影响
                map.put(num - left, sum);
                map.put(num + right, sum);
            }else {
                continue;
            }
        }
        return res;
    }
}
