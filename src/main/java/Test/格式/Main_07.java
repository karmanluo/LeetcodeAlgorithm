package Test.格式;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/20 23:06
 */
public class Main_07 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String[] s = new String[n];
        for (int i = 0; i < n; ++i) {
            s[i] = sc.next();
        }
        Arrays.sort(s);
        for (String si : s) {
            System.out.print(si + " ");
        }
    }
}
