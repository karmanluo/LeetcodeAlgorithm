package 剑指offer.N13机器人的运动范围;

public class GoodSolution {
    //带有递归的DFS,不用借助栈的数据结构来完成，，，，记住 Stack 和 DFS 的关联，从本题目中多总结
    int count;
    boolean[][] visited;

    public int movingCount(int m, int n, int k) {
        if (m <= 0 || n <= 0 || k < 0) return 0;
        visited = new boolean[m][n];
        dfs(0, 0, m, n, k);
        return count;
    }

    public void dfs(int i, int j, int m, int n, int k) {
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] == true) return;

        if (!canK(i, j, k)) return;
        visited[i][j] = true;
        count++;
        dfs(i + 1, j, m, n, k);
        dfs(i - 1, j, m, n, k);
        dfs(i, j + 1, m, n, k);
        dfs(i, j - 1, m, n, k);
    }

    public boolean canK(int i, int j, int k) {
        int sum = 0;
        while (i != 0) {
            sum += i % 10;
            i /= 10;
        }
        while (j != 0) {
            sum += j % 10;
            j /= 10;
        }
        return sum <= k;
    }
}