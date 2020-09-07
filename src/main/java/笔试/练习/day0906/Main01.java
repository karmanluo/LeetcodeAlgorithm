package 笔试.练习.day0906;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main01 {

    static int A;
    static String Pattern;
    //static int n;
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }

        A = Integer.parseInt(list.get(list.size() - 2));
        Pattern = list.get(list.size() - 1);

        String target = doSomething(Pattern, A);

        for (int i = 0; i < list.size() - 2; i++) {
            String s = doSomething(list.get(i), A);
            if (s.indexOf(target) != -1) System.out.println(list.get(i));
        }
    }

    private static String doSomething(String str, int n) {
        StringBuilder sb = new StringBuilder();

        for (char ch : str.toCharArray()) {
            if (ch - '0'< n) {
                sb.append(ch);
            }
        }

        return sb.toString();
    }
}
