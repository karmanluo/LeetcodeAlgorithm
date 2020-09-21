package 笔试.练习.day0919;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int num = sc.nextInt();

            int r = method(num);
            System.out.println(r);
        }
    }

    private static int method(int num) {
        int count = 0;
        while(num != 0) {
            count += (num & 1);
            num = num >> 1;
        }

        return count;
    }
}
