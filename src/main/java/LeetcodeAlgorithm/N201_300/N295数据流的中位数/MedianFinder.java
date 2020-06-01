package LeetcodeAlgorithm.N201_300.N295数据流的中位数;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    PriorityQueue<Integer> small;
    PriorityQueue<Integer> large;
    /** initialize your data structure here. */
    public MedianFinder() {
        //中位数左半部分，用大顶堆
        small = new PriorityQueue<>(Collections.reverseOrder());
        //中位数右半部分，用小顶堆
        large = new PriorityQueue<>();
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
            return (small.peek() + large.peek()) / 2.0;
        else {
            return small.peek();
        }
    }
}