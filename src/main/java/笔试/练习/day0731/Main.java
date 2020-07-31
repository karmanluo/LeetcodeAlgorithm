package 笔试.练习.day0731;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] money = {1, 5, 10, 50, 100};
        int[] nums = new int[5];

        int cnt = 0;
        for (int i = 0; i < 5; i++) nums[i] = sc.nextInt();
        int target = sc.nextInt();

        for (int i = 4; i >= 0; i--) {
            while (target >= money[i] && nums[i] > 0) {
                target -= money[i];
                cnt++;
                nums[i]--;
            }
        }

        System.out.println(cnt);
    }
}
