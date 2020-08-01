package AcWing.基础算法.N高精度乘法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        String b = br.readLine();

        int[] A = new int[a.length()];
        int[] B = new int[b.length()];
        for (int i = 0; i < a.length(); i++) A[i] = a.charAt(a.length() - 1 - i) - '0';
        for (int i = 0; i < b.length(); i++) B[i] = b.charAt(b.length() - 1 - i) - '0';

        int[] res = mul(A, B);

        for (int i = res.length - 1; i >= 0; i--) System.out.print(res[i]);
    }

    private static int[] mul(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;
        int[] C = new int[lenA + lenB];

        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                C[i + j] += A[i] * B[j];
            }
        }

        for(int i = 0, t = 0; i < lenA + lenB || t > 0; i++) {
            t += C[i];
            C[i] = t % 10;
            t /= 10;
        }

        int k  = lenA + lenB - 1;
        while (k > 0 && C[k] == 0) k--;
        return Arrays.copyOf(C, k + 1);
    }
}
