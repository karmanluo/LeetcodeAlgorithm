package 笔试.练习.day0621;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        String s = bf.readLine();
        int n = Integer.parseInt(s);
        String[] arrStr = new String[n];
        for (int i = 0; i < n; i++) {
            arrStr[i] = bf.readLine();
        }

        for (String str : arrStr) {
            String res = del(str);
            System.out.println(res);
        }
        /*
        StringBuilder sb = new StringBuilder("abcd");
        sb.deleteCharAt(0);
        System.out.println(sb.toString());
         */
        /*

         */
        String s1 = "abcde";
        char[] chars = s1.toCharArray();
        chars[2] = '\0';
        System.out.println(String.valueOf(chars).substring(0, chars.length));
    }

    private static String del(String str) {
        char[] chars = str.toCharArray();

        int index = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i > 1 && chars[index - 1] == chars[i] && chars[index - 1] == chars[index - 2]) chars[i] = '\0';
            else if (i > 2 && chars[i] == chars[index - 1] && chars[index - 2] == chars[index - 3]) chars[i] = '\0';
            else {
                chars[index] = chars[i];
                index++;
            }
        }

        return String.valueOf(chars).substring(0, index);
    }
}