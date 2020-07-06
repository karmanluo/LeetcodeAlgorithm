package LeetcodeAlgorithm.N716最大栈;

import java.util.ArrayDeque;
import java.util.Deque;

class MaxStack {

    Deque<Integer> stack;
    Deque<Integer> maxStack;
    /** initialize your data structure here. */
    public MaxStack() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty()) maxStack.push(x);
        else {
            if (maxStack.peek() < x) maxStack.push(x);
            else maxStack.push(maxStack.peek());
        }
    }
    //当栈为空的时候不会出现后四个操作。
    public int pop() {
        maxStack.pop();
        return stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int max = peekMax();
        Deque<Integer> buffer = new ArrayDeque<>();
        while (max != stack.peek()) buffer.push(pop());
        pop();
        while (!buffer.isEmpty()) push(buffer.pop());

        return max;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */