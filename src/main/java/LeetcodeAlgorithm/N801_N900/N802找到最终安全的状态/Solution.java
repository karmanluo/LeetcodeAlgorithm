package LeetcodeAlgorithm.N801_N900.N802找到最终安全的状态;

import java.util.*;

public class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        boolean[] safe = new boolean[n];
        List<HashSet<Integer>> copyG = new ArrayList<>();
        List<HashSet<Integer>> reverseG = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            copyG.add(new HashSet<>());
            reverseG.add(new HashSet<>());
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (graph[i].length == 0) {
                queue.offer(i);
            }
            for (int j : graph[i]) {
                copyG.get(i).add(j);
                reverseG.get(j).add(i);
            }
        }

        while (!queue.isEmpty()) {
            int j = queue.poll();
            safe[j] = true;
            for (int i : reverseG.get(j)) {
                copyG.get(i).remove(j);
                if (copyG.get(i).isEmpty()) {
                    queue.offer(i);
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]){
                res.add(i);
            }
        }

        return res;
    }
}
