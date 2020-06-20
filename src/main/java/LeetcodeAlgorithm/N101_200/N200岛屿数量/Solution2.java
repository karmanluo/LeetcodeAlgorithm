package LeetcodeAlgorithm.N101_200.N200岛屿数量;

class Solution2 {
    private int m;
    private int n;
    private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        m = grid.length;
        if (m == 0) return 0;
        n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    for (int[] dir : dirs) {
                        int x = i + dir[0];
                        int y = j + dir[1];
                        if (x >= 0 && y >= 0 && x < m && y < n && grid[x][y] == '1') {
                            int node1 = i * n + j;
                            int node2 = x * n + y;
                            uf.union(node1, node2);
                        }
                    }
                }
            }
        }

        return uf.count;
    }

    class UnionFind {
        int[] size;
        int[] parent;
        int count;
        int m, n;

        public UnionFind(char[][] grid) {
            m = grid.length;
            n = grid[0].length;
            parent = new int[m * n];
            size = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') {
                        int id = i * n + j;
                        parent[id] = id;
                        size[id] = 1;
                        count++;
                    }
                }
            }
        }

        public int find(int x) {
            if (parent[x] == x) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;

            if (size[rootP] > size[rootQ]) {
                parent[rootQ] = rootP;
                size[rootP] += size[rootQ];
            } else {
                parent[rootP] = rootQ;
                size[rootQ] += size[rootP];
            }

            count--;
        }
    }
}

