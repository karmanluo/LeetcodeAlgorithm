package Test.格式;

import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 22:16
 */
public class Main_03 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        for (; ; ) {
            int a = s.nextInt();
            int b = s.nextInt();
            if (a == 0 && b == 0) {
                break;
            }else {
                System.out.println(a + b);
            }
        }
    }
}