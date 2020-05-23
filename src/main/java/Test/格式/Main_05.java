package Test.格式;

import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 22:36
 */
public class Main_05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            int first = sc.nextInt();
            for (int j = 0; j < first; j++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}
