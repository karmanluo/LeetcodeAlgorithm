package Test.JUC;

import java.util.concurrent.CountDownLatch;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/1 22:29
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //实现按照顺序被灭
        for (int i = 1; i <= 6; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t国，被灭！");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t秦，统一六国！");
    }

    private static void closedoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(8);

        for (int i = 1; i <= 8; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t--------上完自习，离开教室--------");
                countDownLatch.countDown();
            }, String.valueOf(i)).start();
        }

        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t#############班上关门走人###########");
    }
}
