package LeetcodeAlgorithm.N201_300.N239滑动窗口最大值;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];

        int n = nums.length;
        int[] r = new int[n - k + 1];
        int ri = 0;
        //dq用来存的是数组index
        Deque<Integer> dq = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            //如果双端队列里面有K的元素了，就不能再加了，得先去掉队头index
            if (!dq.isEmpty() && (i - dq.peek() + 1) > k) {
                dq.poll();
            }
            //如果队尾存的index对应的num[index]比进来的值num[i]要小，说明没用，不要了
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }
            dq.offer(i);
            if (i + 1 - k >= 0){
                r[ri++] = nums[dq.peek()];
            }
        }
        return r;
    }
}
