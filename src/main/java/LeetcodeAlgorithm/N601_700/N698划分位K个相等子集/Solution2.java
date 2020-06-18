package LeetcodeAlgorithm.N601_700.N698划分位K个相等子集;

import java.util.Arrays;

public class Solution2 {
    public boolean canPartitionKSubsets(int[] A, int k) {
        if (k > A.length) return false;
        int sum = 0;
        for (int num : A) sum += num;
        if (sum % k != 0) return false;
        boolean[] visited = new boolean[A.length];
        Arrays.sort(A);
        return dfs(A, A.length - 1, visited, 0, sum / k, k);
    }

    public boolean dfs(int[] A, int st, boolean[] visited, int sum, int target, int k) {
        if (k == 0) return true;
        if (sum == target && dfs(A, A.length - 1, visited, 0, target, k - 1))
            return true;
        for (int i = st; i >= 0; --i) {
            if (!visited[i] && sum + A[i] <= target) {
                visited[i] = true;
                if (dfs(A, i - 1, visited, sum + A[i], target, k))
                    return true;
                visited[i] = false;
            }
        }
        return false;
    }
}
