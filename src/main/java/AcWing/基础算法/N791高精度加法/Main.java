package AcWing.基础算法.N791高精度加法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String A = br.readLine();
        String B = br.readLine();

        int[] res = new int[100010];

        int lenA = A.length(), lenB = B.length();
        int i = lenA - 1, j = lenB - 1, k = 0, carry = 0;
        while (i >= 0 || j >= 0) {
            if (i >= 0) carry += A.charAt(i) - '0';
            if (j >= 0) carry += B.charAt(j) - '0';
            res[k++] = carry % 10;
            carry /= 10;
            i--; j--;
        }
        if (carry > 0) res[k++] = carry;

        for (int l = k - 1; l >= 0; l--) System.out.print(res[l]);
    }
}
