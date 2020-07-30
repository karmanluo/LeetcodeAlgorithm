package LeetcodeAlgorithm.N0___100.N12整数转罗马数字;

public class Solution {
    public String intToRoman(int num) {
        int[] numArr = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] str = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numArr.length; i++) {
            while (num >= numArr[i]) {
                num -= numArr[i];
                sb.append(str[i]);
            }
        }

        return sb.toString();
    }
}
