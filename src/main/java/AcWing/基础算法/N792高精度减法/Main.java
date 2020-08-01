package AcWing.基础算法.N792高精度减法;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        int[] A = new int[a.length];
        int[] B = new int[b.length];
        for (int i = a.length - 1, j = 0; i >= 0; j++, i--) A[j] = a[i] - '0';
        for (int i = b.length - 1, j = 0; i >= 0; j++, i--) B[j] = b[i] - '0';

        A = trimZero(A); B = trimZero(B);

        if (cmp(A, B)) sub(A, B);
        else {
            System.out.print("-");
            sub(B, A);
        };
    }

    private static int[] trimZero(int[] A) {
        int lastPos = A.length - 1;
        while (lastPos > 0 && A[lastPos] == 0) lastPos--;

        return Arrays.copyOf(A, lastPos + 1);
    }

    private static void sub(int[] A, int[] B) {
        int lenA = A.length, lenB = B.length;

        int[] res = new int[100010];
        int t = 0, k = 0;
        for (int i = 0; i < lenA; i++) {
            t = A[i] - t;
            if (i < lenB) t -= B[i];
            res[k++] = (t + 10) % 10;
            if (t < 0) t = 1;
            else t = 0;
        }
        res = Arrays.copyOf(res, k);
        for (int i = 0; i < k; i++) System.out.println(res[i]);

        res = trimZero(res);

        for (int i = res.length - 1; i >= 0; i--) System.out.print(res[i]);
    }

    // A >= B ?
    private static boolean cmp(int[] A, int[] B) {
        if (A.length != B.length) return A.length > B.length;
        else {
            for (int i = A.length - 1; i >= 0; i--) {
                if (A[i] != B[i]) return A[i] > B[i];
            }
            return true;
        }
    }
}
