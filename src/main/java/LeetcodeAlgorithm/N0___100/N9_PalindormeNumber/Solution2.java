package LeetcodeAlgorithm.N0___100.N9_PalindormeNumber;

public class Solution2 {
    public boolean isPalindrome(int x) {
        if (x < 0) return false;

        int y = x, rev = 0;

        while (y != 0){
            rev = rev * 10 + y % 10;
            y /= 10;
        }
        return x == rev;
    }
}
