package LeetcodeAlgorithm.N301_400.N347前K个高频元素;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer>[] bucket = new List[nums.length + 1];
        Map<Integer, Integer> frequentMap = new HashMap<>();

        for (int n : nums){
            frequentMap.put(n, frequentMap.getOrDefault(n, 0) + 1);
        }

        for (int key : frequentMap.keySet()){
            int frequency = frequentMap.get(key);
            if (bucket[frequency] == null){
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);
        }

        List<Integer> res = new ArrayList<>();
        for (int pos = bucket.length - 1; pos >= 0; pos--) {
            if (bucket[pos] != null){
                for (int i = 0; i < bucket[pos].size() && res.size() < k; i++) {
                    res.add(bucket[pos].get(i));
                }
            }
        }
        return res;
    }
}
