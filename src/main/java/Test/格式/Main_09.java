package Test.格式;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 23:43
 */
public class Main_09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String[] s = sc.nextLine().split(",");
            Arrays.sort(s);
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i]);
                if(i != s.length - 1)
                    System.out.print(",");
            }
            System.out.println();
        }
    }
}
