package Test.day0426;

import java.util.*;

public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        Queue<Integer> q = new LinkedList<>();
        int notHaveAny = -1;


        sc.nextLine();
        for (int i = 0; i < T; i++) {
            int n = sc.nextInt();
            sc.nextLine();
            for (int j = 0; j < n; j++) {
                String[] s = sc.nextLine().split(" ");
                String str = s[0];
                if (str.equals("PUSH")) {
                    int num = Integer.parseInt(s[1]);
                    q.offer(num);
                }
                if (str.equals("TOP")) {
                    if (q.size() == 0) {
                        System.out.println(notHaveAny);
                    } else {
                        System.out.println(q.peek());
                    }
                }
                if (str.equals("POP")) {
                    if (q.isEmpty()) {
                    } else {
                        q.poll();
                    }
                }
                if (str.equals("SIZE")) {
                    System.out.println(q.size());
                }
            }
            q.clear();
        }
    }
}