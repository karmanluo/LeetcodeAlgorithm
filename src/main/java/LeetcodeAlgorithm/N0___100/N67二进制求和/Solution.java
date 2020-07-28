package LeetcodeAlgorithm.N0___100.N67二进制求和;

public class Solution {
    public String addBinary(String a, String b) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int x = (i >= 0) ? (a.charAt(i) - '0') : 0;
            int y = (j >= 0) ? (b.charAt(j) - '0') : 0;
            int sum = x + y + carry;
            carry = sum / 2;
            res.append(sum % 2);
        }
        if (carry > 0) res.append(carry);

        return res.reverse().toString();
    }
}
