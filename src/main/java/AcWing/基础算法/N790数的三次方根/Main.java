package AcWing.基础算法.N790数的三次方根;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double t = sc.nextDouble();

        double l = -10000, r = 10000;
        while (r - l > 1e-8) {
            double m = (l + r) / 2;
            if (m * m * m >= t) r = m;
            else l = m;
        }

        System.out.println(String.format("%.6f", l));
    }
}
