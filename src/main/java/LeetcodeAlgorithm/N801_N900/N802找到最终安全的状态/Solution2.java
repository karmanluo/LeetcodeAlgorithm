package LeetcodeAlgorithm.N801_N900.N802找到最终安全的状态;

import java.util.ArrayList;
import java.util.List;

public class Solution2 {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int len = graph.length;
        int[] color = new int[len];

        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if (dfs(i, color, graph)){
                res.add(i);
            }
        }

        return res;
    }

    private boolean dfs(int node, int[] color, int[][] graph) {
        if (color[node] > 0){
            return color[node] == 2;
        }

        color[node] = 1;
        for (int neighbor : graph[node]) {
            if (color[neighbor] == 2){
                continue;
            }
            if (color[neighbor] == 1 || !dfs(neighbor, color, graph)){
                return false;
            }
        }

        color[node] = 2;
        return true;
    }
}
