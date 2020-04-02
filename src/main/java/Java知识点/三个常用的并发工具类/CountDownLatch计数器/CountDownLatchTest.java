package Java知识点.三个常用的并发工具类.CountDownLatch计数器;

import java.util.concurrent.CountDownLatch;

/**
 * 每当一个线程执行cdl.countDown()的方法，计数器的值就会减1。当初始值到达0时，
 * 它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cd1 = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +", begin...");
                cd1.countDown();
                System.out.println(Thread.currentThread().getName() + ", end...");
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +", begin...");
                cd1.countDown();//计数器每次减一
                System.out.println(Thread.currentThread().getName() + ", end...");
            }
        }).start();

        cd1.await();// 減去为0,恢复任务继续执行
        System.out.println("两个Thread执行完毕...");
        System.out.println("主线程继续执行...");
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ", i: " + i);
        }
    }
}
