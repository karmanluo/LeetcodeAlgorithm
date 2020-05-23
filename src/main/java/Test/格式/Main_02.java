package Test.格式;

import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 20:22
 */
public class Main_02 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int lineNumber = s.nextInt();
        for (int i = 0; i < lineNumber; i++) {
            int a = s.nextInt();
            int b = s.nextInt();
            System.out.println(a + b);
        }
    }
}