package Java知识点.多线程.按序打印;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/9 11:44
 */
class Foo {
    CountDownLatch cd1;
    CountDownLatch cd2;
    public Foo() {
        cd1 = new CountDownLatch(1);
        cd2 = new CountDownLatch(1);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        cd1.countDown();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        cd1.await();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        cd2.countDown();
    }

    public void third(Runnable printThird) throws InterruptedException {
        cd2.await();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }
}
