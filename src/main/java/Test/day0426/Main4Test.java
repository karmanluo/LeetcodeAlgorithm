package Test.day0426;

import java.util.Scanner;
import java.util.Stack;


/**
 * @Author:KunmingLuo
 * @Date: 2020/4/27 15:56
 */
public class Main4Test {
    public static void push(Stack s1, int n) {
        s1.push(n);
    }

    public static int pop(Stack s1, Stack s2) {
        if (s2.size() == 0) {
            while (!s1.isEmpty()) {
                int tmp = (int) s1.peek();
                s2.push(tmp);
                s1.pop();
            }
        }
        int p = (int) s2.peek();
        s2.pop();
        return p;
    }

    public static int top(Stack s1, Stack s2) {
        if (s2.size() == 0) {
            while (!s1.isEmpty()) {
                int tmp = (int) s1.peek();
                s2.push(tmp);
                s1.pop();
            }
        }
        int p = (int) s2.peek();
        return p;
    }

    public static void main(String[] args) {
        // write your code here
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Stack<Integer> st1 = new Stack<>();
        Stack<Integer> st2 = new Stack<>();
        String enter = sc.nextLine();
        for (int i = 0; i < n; i++) {
            String s = sc.nextLine();
            String[] tmp = s.split(" ");
            if (tmp.length == 1) {
                switch (tmp[0]) {
                    case "peek": {
                        if (st1.size() == 0 && st2.size() == 0) {
                            System.out.println(-1);
                        } else {
                            System.out.println(top(st1, st2));
                        }
                        break;
                    }
                    case "poll": {
                        if (st1.size() == 0 && st2.size() == 0) {
                            System.out.println(-1);
                        } else {
                            pop(st1, st2);
                        }
                        break;
                    }
                }
            } else {
                int x = Integer.valueOf(tmp[1]);
                push(st1, x);
            }
        }
    }
}

