package AcWing.基础算法.N798差分矩阵;

import java.io.*;

/*
        输入一个n行m列的整数矩阵，再输入q个操作，每个操作包含五个整数x1, y1, x2, y2, c，其中(x1, y1)和(x2, y2)表示一个子矩阵的左上角坐标和右下角坐标。

    每个操作都要将选中的子矩阵中的每个元素的值加上c。

    请你将进行完所有操作后的矩阵输出。

    输入格式
    第一行包含整数n,m,q。

    接下来n行，每行包含m个整数，表示整数矩阵。

    接下来q行，每行包含5个整数x1, y1, x2, y2, c，表示一个操作。

    输出格式
    共 n 行，每行 m 个整数，表示所有操作进行完毕后的最终矩阵。

    数据范围
    1≤n,m≤1000,
    1≤q≤100000,
    1≤x1≤x2≤n,
    1≤y1≤y2≤m,
    −1000≤c≤1000,
    −1000≤矩阵内元素的值≤1000
    输入样例：
    3 4 3
    1 2 2 1
    3 2 2 1
    1 1 1 1
    1 1 2 2 1
    1 3 2 3 2
    3 1 3 4 1
    输出样例：
    2 3 4 1
    4 3 4 1
    2 2 2 2
 */
public class Main {

    static int N = 1010;
    public static void main(String[] args) throws IOException {
        //普遍Scanner会超时，所以使用BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line1 = br.readLine().split(" ");
        int n = Integer.parseInt(line1[0]), m = Integer.parseInt(line1[1]), q = Integer.parseInt(line1[2]);

        // 数组 a 表示结果数组， 数组 b 代表差分数组
        int[][] a = new int[N][N], b = new int[N][N];
        for (int i = 1; i <= n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                insert(b, i, j, i, j, Integer.parseInt(line[j - 1]));
            }
        }

        while (q-- > 0) {
            String[] line = br.readLine().split(" ");
            int x1 = Integer.parseInt(line[0]);
            int y1 = Integer.parseInt(line[1]);
            int x2 = Integer.parseInt(line[2]);
            int y2 = Integer.parseInt(line[3]);
            int c = Integer.parseInt(line[4]);
            insert(b, x1, y1, x2, y2, c);
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                a[i][j] = a[i][j - 1] + a[i - 1][j] - a[i - 1][j - 1] + b[i][j];
                bw.write(a[i][j] + " ");
            }
            bw.write("\n");
        }
        //所有write下的内容，会先存在writers中，当启用flush以后，会输出存在其中的内容。如果没有调用flush，则不会将writer中的内容进行输出。
        bw.flush();
        bw.close();
        br.close();
    }

    private static void insert(int[][] b, int x1, int y1, int x2, int y2, int c) {
        b[x1][y1] += c;
        b[x2 + 1][y1] -= c;
        b[x1][y2 + 1] -= c;
        b[x2 + 1][y2 + 1] += c;
    }
}
