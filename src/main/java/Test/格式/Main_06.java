package Test.格式;

import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 22:48
 *
 * 链接：https://ac.nowcoder.com/acm/contest/320/G
 * 来源：牛客网
 *
 * 题目描述
 * 计算一系列数的和
 * 输入描述:
 * 输入数据有多组, 每行表示一组输入数据。
 *
 * 每行不定有n个整数，空格隔开。(1 <= n <= 100)。
 * 输出描述:
 * 每组数据输出求和的结果
 * 示例1
 * 输入
 * 复制
 * 1 2 3
 * 4 5
 * 0 0 0 0 0
 * 输出
 * 复制
 * 6
 * 9
 * 0
 */
public class Main_06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String[] s = sc.nextLine().split(" ");
            int sum = 0;
            for (int i = 0; i < s.length; i++) {
                sum += Integer.parseInt(s[i]);
            }
            System.out.println(sum);
        }
    }
}
