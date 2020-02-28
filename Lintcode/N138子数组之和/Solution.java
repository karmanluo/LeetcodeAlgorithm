package Lintcode.N138子数组之和;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    //解题思路：用hashmap存历史sum的和以及对应的index
    //当出现的sum值出现在hashmap中的时候，说明存在子序列导致sum没变
    //也就是说存在了和为 0 的子序列

    //思路延申，也可以求和为 x 的子序列
    public List<Integer> subarraySum(int[] nums) {
        //拿来存中间的sum值以及对应的index
        HashMap<Integer, Integer> map = new HashMap<>();
        //存结果的index
        List<Integer> res = new ArrayList<>();

        //初始化map
        map.put(0, -1);
        for (int i = 0, sum = 0; i < nums.length; i++) {
            sum += nums[i];
            //表示sum在之前的map记录过，表示找到了子序列为0的结果
            if (map.containsKey(sum)){
                res.add(map.get(sum) + 1);
                res.add(i);
            }

            map.put(sum, i);
        }
        return res;
    }
}
