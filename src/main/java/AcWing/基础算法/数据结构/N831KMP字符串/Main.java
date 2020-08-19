package AcWing.基础算法.数据结构.N831KMP字符串;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String P = br.readLine();
        // 模式串p
        char[] p = new char[n + 1];
        for (int i = 1; i <= n; i++) p[i] = P.charAt(i - 1);

        int m = Integer.parseInt(br.readLine());
        String S = br.readLine();
        // 总串s
        char[] s = new char[m + 1];
        for (int i = 1; i <= m; i++) s[i] = S.charAt(i - 1);

        // 构造前缀数组
        int prefix[] = new int[n + 1];
        for (int i = 2, j = 0; i <= n; i++) {
            //i从2开始，因为prefix[1]肯定为0
            while (j != 0 && p[i] != p[j + 1]) j = prefix[j];
            if (p[i] == p[j + 1]) prefix[i] = ++j;
        }

        // kmp匹配
        for (int i = 1, j = 0; i <= m; i++) {
            while (j != 0 && s[i] != p[j + 1]) j = prefix[j]; // s[i] != p[j + 1]即不匹配，则往后移动

            if (s[i] == p[j + 1]) j++; // 匹配时将j++进行下一个字符得匹配
            if (j == n) { // 匹配了n字符了即代表完全匹配了
                bw.write(i - n + " ");
                j = prefix[j]; // 完全匹配后继续搜索
            }
        }
        bw.flush();
        br.close();
        bw.close();
    }

}