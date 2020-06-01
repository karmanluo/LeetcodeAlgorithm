package LeetcodeAlgorithm.N201_300.N207课程表;

import java.util.*;

class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] indegree = new int[numCourses];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int[] pair : prerequisites) {
            indegree[pair[0]]++;
        }
        for (int i = 0; i < indegree.length; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        int select = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            res[select++] = course;
            for (int[] pair : prerequisites) {
                if (pair[1] == course) {
                    indegree[pair[0]]--;
                    if (indegree[pair[0]] == 0) {
                        queue.add(pair[0]);
                    }
                }
            }
        }
        return numCourses == select ? res : new int[]{};
    }
}
