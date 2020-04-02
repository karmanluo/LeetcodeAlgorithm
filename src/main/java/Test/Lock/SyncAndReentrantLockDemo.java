package Test.Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 10:56
 */
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        synchronized (new Object()){

        }

        new ReentrantLock();
    }
}
