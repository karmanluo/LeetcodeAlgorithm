package Test.day0426;

import java.util.*;


public class Main4 {

    public static void push(Stack s1, int n) {
        s1.push(n);
    }

    public static int peek(Stack s1, Stack s2) {
        if (s2.isEmpty()){
            while(!s1.isEmpty())
                s2.push(s1.pop());
        }
        return (int)s2.peek();
    }

    public static int poll(Stack s1, Stack s2) {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return (int)s2.pop();
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        sc.nextLine();
        for (int i = 0; i < N; i++) {
            String[] str = sc.nextLine().split(" ");

            if (str.length == 1) {
                if (str[0].equals("peek")) {
                    System.out.println(peek(s1, s2));
                }
                if (str[0].equals("poll")) {
                    poll(s1, s2);
                }
            }

            if (str.length == 2) {
                if (str[0].equals("add")){
                    int x = Integer.valueOf(str[1]);
                    push(s1, x);
                }
            }
        }
    }
}