package LeetcodeAlgorithm.N0___100.N13_RomantoInteger;

public class Solution {
    public static int romanToInt(String s) {
        int sum = 0;
        if (s.indexOf("IV") != -1) sum -= 2;
        if (s.indexOf("IX") != -1) sum -= 2;
        if (s.indexOf("XL") != -1) sum -= 20;
        if (s.indexOf("XC") != -1) sum -= 20;
        if (s.indexOf("CD") != -1) sum -= 200;
        if (s.indexOf("CM") != -1) sum -= 200;
        char c[] = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            if (c[i] == 'M') sum += 1000;
            if (c[i] == 'D') sum += 500;
            if (c[i] == 'C') sum += 100;
            if (c[i] == 'L') sum += 50;
            if (c[i] == 'X') sum += 10;
            if (c[i] == 'V') sum += 5;
            if (c[i] == 'I') sum += 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println("XCIX的结果是： " + romanToInt("XCIX"));
    }
}
