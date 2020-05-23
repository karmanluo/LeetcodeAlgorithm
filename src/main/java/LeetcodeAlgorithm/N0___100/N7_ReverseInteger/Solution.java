package LeetcodeAlgorithm.N0___100.N7_ReverseInteger;

public class Solution {
    public static int reverse(int x) {
        int rev = 0;
        while (x != 0) {
            int pop = x % 10;
            x /= 10;
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) return 0;
            rev = pop + rev * 10;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println("数字 -454547 的结果是： " + reverse(-454547));
        System.out.println("数字 123456 的结果是： " + reverse(123456));
        /*System.out.println(Integer.MAX_VALUE - Integer.MAX_VALUE / 10 * 10);
        System.out.println(Integer.MIN_VALUE - Integer.MIN_VALUE / 10 * 10);*/
    }
}
