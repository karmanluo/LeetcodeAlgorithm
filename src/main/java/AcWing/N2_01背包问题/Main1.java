package AcWing.N2_01背包问题;

import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
        }
        reader.close();

        // 正式算法的代码
        // 将dp优化为一维数组
        /*
        注意，这里第二层循环的时候，还是小到大循环的话，那么

        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])
        实际上变成了
        dp[i][j] = Math.max(dp[i][j], dp[i][j-v[i]] + w[i]);

        因为i-1的值已经在前面被更新过了，覆盖了
        为了避免这个问题，所以要逆序更新，即先更新第i个，然后更新第i-1个，从而保证第i-1个不被覆盖

        输入样例
        4 5
        1 2
        2 4
        3 4
        4 5
        输出样例：
        8

        如果不逆序的话，输出结果为 8，dp数组实际为：
        0 0 0 0 0 0
        0 2 2 2 2 2
        0 2 4 6 6 6
        0 2 4 6 6 8
        0 2 4 6 6 8
        */
        int[] dp = new int[V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = V; j >= v[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
            /*for (int j = 0; j <= V; j++) {
                System.out.print(dp[j] + " ");
            }
            System.out.println();*/
        }

        System.out.println(dp[V]);
    }
}