package Test.day0427;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/27 22:19
 */

import java.io.*;

public class Main03 {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(bf.readLine());
        String s = bf.readLine();

        System.out.println(method(s, n));
    }

    public static char method(String s, int n) {
        char c0 = 'N', c1 = 'E', c2 = 'S', c3 = 'W';
        int flag = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'L': {
                    if (flag == 0) {
                        flag = 3;
                    } else {
                        flag--;
                    }
                    break;
                }
                case 'R': {
                    if (flag == 3) {
                        flag = 0;
                    } else {
                        flag++;
                    }
                    break;
                }
            }
        }

        if (flag == 0) {
            return c0;
        } else if (flag == 1) {
            return c1;
        } else if (flag == 2) return c2;
        else return c3;
    }
}