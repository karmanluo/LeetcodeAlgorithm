package Test.Lock;

import java.util.concurrent.TimeUnit;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 23:16
 */
class HoldLockThread implements Runnable{
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"，\t尝试获得"+lockB);
            try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  e.printStackTrace(); }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"\t自己持有"+lockA+"，\t并且获得"+lockB);
            }
        }
    }
}
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA, lockB), "线程AA").start();
        new Thread(new HoldLockThread(lockB, lockA), "线程BB").start();
    }
}
