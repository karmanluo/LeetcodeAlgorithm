package LeetcodeAlgorithm.N215数组中第K个最大元素;

import java.util.PriorityQueue;

/**
 * 使用优先队列的思想来做
 */
public class Solution2 {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();

        for (int val : nums) {
            priorityQueue.offer(val);
            if (priorityQueue.size() > k)
                priorityQueue.poll();
        }
        return priorityQueue.peek();
    }
}
