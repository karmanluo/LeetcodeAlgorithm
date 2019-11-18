package 面试.全国编程比赛;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入数字N：");
        int N = sc.nextInt();
        int count = count(N);
        System.out.println(count);
    }

    public static int count(int N){
        int res, count = 0;

        for (int i = 1; i < 1000; i++) {
            res = i * i;
            if (res > N) return count;
            count++;
        }
        return 0;
    }
}
