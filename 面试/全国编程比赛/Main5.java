package 面试.全国编程比赛;

import java.util.Scanner;

public class Main5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt();

        int data[] = new int[N];
        for (int i = 0; i < N; i++){
            data[i] = sc.nextInt();
        }


        String line = sc.nextLine();
        String p[] = line.split("\\*");
        int res = helper(p[0]);
        for (int x = 1; x < p.length; x++)
            res *= helper(p[x]);
        System.out.println(res);
    }

    public static int helper(String s) {
        String p[] = s.split("\\+");
        int result = Integer.valueOf(p[0]);
        for (int x = 1; x < p.length; x++) {
            result += Integer.valueOf(p[x]);
        }
        return result;
    }
}
