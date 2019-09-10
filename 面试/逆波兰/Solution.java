package 面试.逆波兰;

import java.util.Scanner;
import java.util.Stack;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int len = input.length;

        Stack<String> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            if (input[i].equals("+")) {
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) + Integer.parseInt(stack.pop())));
            } else if (input[i].equals("-")) {
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(a - b));
            } else if (input[i].equals("*")) {
                stack.push(String.valueOf(Integer.parseInt(stack.pop()) * Integer.parseInt(stack.pop())));
            } else if (input[i].equals("/")) {
                int b = Integer.parseInt(stack.pop());
                int a = Integer.parseInt(stack.pop());
                stack.push(String.valueOf(a / b));
            } else {
                stack.push(input[i]);
            }
        }
        System.out.println(stack.pop());
    }

}
