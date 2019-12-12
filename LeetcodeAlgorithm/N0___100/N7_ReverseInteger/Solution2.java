package LeetcodeAlgorithm.N7_ReverseInteger;

public class Solution2 {
    public static int reverse(int x) {
        long res = 0;
        while (x != 0) {
            res = x % 10 + res * 10;
            x /= 10;
        }
        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) return 0;
        return (int)res;
    }

    public static void main(String[] args) {
        System.out.println("数字 -454547 的结果是： " + reverse(-454547));
        System.out.println("数字 123456 的结果是： " + reverse(123456));
        /*System.out.println(Integer.MAX_VALUE - Integer.MAX_VALUE / 10 * 10);
        System.out.println(Integer.MIN_VALUE - Integer.MIN_VALUE / 10 * 10);*/
    }
}
