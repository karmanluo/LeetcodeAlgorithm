package Test.Lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/1 18:18
 */
public class SpinLockDemo {
    //线程原子引用，默认值是null
    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void mylock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t---------come in -------");
        while (!atomicReference.compareAndSet(null, thread)){

        }
    }

    public void myunlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(Thread.currentThread().getName()+"\t===========invoke unlock===========");
    }


    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(()->{
            spinLockDemo.mylock();
            try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {  e.printStackTrace(); }
            spinLockDemo.myunlock();
        }, "AA").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {  e.printStackTrace(); }
        new Thread(()->{
            spinLockDemo.mylock();
            try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {  e.printStackTrace(); }
            spinLockDemo.myunlock();
        }, "BB").start();
    }
}
