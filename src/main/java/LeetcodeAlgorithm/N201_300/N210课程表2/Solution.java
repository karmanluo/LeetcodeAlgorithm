package LeetcodeAlgorithm.N201_300.N210课程表2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer>[] neigh = new List[numCourses];
        int[] indegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) neigh[i] = new ArrayList<>();
        for (int[] pair : prerequisites) {
            neigh[pair[1]].add(pair[0]);
            indegree[pair[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }

        int[] res = new int[numCourses];
        int cnt = 0;
        for (; !queue.isEmpty(); cnt++) {
            int node = queue.poll();
            res[cnt] = node;
            for (Integer neiNode : neigh[node]) {
                if (--indegree[neiNode] == 0) queue.offer(neiNode);
            }
        }
        if (cnt == numCourses)
            return res;

        return new int[0];
    }
}
