package LeetcodeAlgorithm.N301_400.N347前K个高频元素;

import java.util.*;

public class Solution2 {
    public List<Integer> topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> frequentMap = new HashMap<>();
        for (int n : nums) {
            frequentMap.put(n, frequentMap.getOrDefault(n, 0) + 1);
        }
        //小根堆好
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());
        for (Map.Entry<Integer, Integer> entry : frequentMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        List<Integer> res = new ArrayList<>();
        while (res.size() < k) {
            res.add(minHeap.poll().getKey());
        }
        return res;
    }
}
