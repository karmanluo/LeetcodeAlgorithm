package LeetcodeAlgorithm.N501_600.N504七进制数;

public class Solution {
    public String convertToBase7(int num) {
        StringBuilder res = new StringBuilder();
        boolean isNag = false;
        if (num < 0) {
            num *= -1;
            isNag = true;
        } else if (num == 0) return res.append(0).toString();

        while (num > 0) {
            res.append(num % 7);
            num /= 7;
        }

        if (isNag) res.append("-");
        return res.reverse().toString();
    }
}
