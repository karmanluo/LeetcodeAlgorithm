package Test.格式;

import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 22:18
 */
public class Main_04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNext("0")){
            int p = sc.nextInt();
            int sum = 0;
            for (int i = 0; i < p; i++) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}
