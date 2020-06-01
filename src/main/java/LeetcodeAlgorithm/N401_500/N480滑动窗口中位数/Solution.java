package LeetcodeAlgorithm.N401_500.N480滑动窗口中位数;

import java.util.PriorityQueue;

public class Solution {
    PriorityQueue<Integer> small = new PriorityQueue<>((i1, i2)->i2.compareTo(i1));
    PriorityQueue<Integer> large = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length - k + 1;
        if (n < 0) return new double[0];

        double[] res = new double[n];
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k){
                res[i - k] = findMedian();
                remove(nums[i - k]);
            }
            if (i < nums.length){
                addNum(nums[i]);
            }
        }
        return res;
    }

    private void remove(int num) {
        if (num > findMedian())
            large.remove(num);
        else
            small.remove(num);

        if ((small.size() - large.size()) > 1){
            large.offer(small.poll());
        }
        if (small.size() < large.size()){
            small.offer(large.poll());
        }
    }

    public void addNum(int num) {
        //一个数进来，左边是大顶堆
        small.offer(num);
        //每次进都是先进左边，然后到右边，通过poll可以保证到右边的是大的数
        large.offer(small.poll());
        //每次到右边会导致右边的数更多，左边小的时候，将右边小顶堆的值放到左边去
        if (small.size() < large.size()){
            small.offer(large.poll());
        }
    }

    public double findMedian() {
        if (small.size() == large.size())
            return ((double)small.peek() + (double)large.peek()) / 2.0;
        else {
            return small.peek();
        }
    }
}
