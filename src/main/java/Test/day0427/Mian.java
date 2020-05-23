package Test.day0427;

import java.util.*;

//ac 100 %
class Main {

    public static long computeNum(int x) {
        long sum = 0;
        for (int i = 1; i <= x; i++) {
            sum += i;
        }
        long r = sum % 3;
        return r;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int x = sc.nextInt();
        int y = sc.nextInt();

        int count = 0;
        long mod = computeNum(x);
        for (int i = x + 1; i <= y; i++) {
            if (mod == 0) count++;
            mod = (i + mod) % 3;
        }
        if (mod == 0) count++;

        System.out.println(count);
    }
}