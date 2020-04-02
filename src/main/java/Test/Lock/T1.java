package Test.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/1 16:06
 */
public class T1 {
    volatile int n = 0;

    private void add(){
        n++;
    }

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock lock1 = new ReentrantLock(true);
    }
}
