package Test.Lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 10:56
 *
 *      题目：多线程之间按顺序调用，实现A->B->C三个线程启动，要求如下：
 *      AA打印AA，BB打印BB，CC打印CC
 *      紧接着
 *      AA打印AA，BB打印BB，CC打印CC
 *      。。。。。。。
 *      来3轮
 */
class ShareResources{
    private int number = 1;     //A:1    B:2     C:3
    private Lock lock = new ReentrantLock();
    private Condition c1 = lock.newCondition();
    private Condition c2 = lock.newCondition();
    private Condition c3 = lock.newCondition();

    public void printAA(){
        lock.lock();
        try{
            //1.判断
            while (number != 1){
                c1.await();
            }
            //2.干活
            System.out.println(Thread.currentThread().getName()+"\tAA");
            number = 2;
            //3.通知
            c2.signal();
         }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printBB(){
        lock.lock();
        try{
            //1.判断
            while (number != 2){
                c2.await();
            }
            //2.干活
            System.out.println(Thread.currentThread().getName()+"\tBB");
            number = 3;
            //3.通知
            c3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public void printCC(){
        lock.lock();
        try{
            //1.判断
            while (number != 3){
                c3.await();
            }
            //2.干活
            System.out.println(Thread.currentThread().getName()+"\tCC");
            number = 1;
            //3.通知
            c1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class SyncAndReentrantLockDemo {
    public static void main(String[] args) {
        ShareResources shareResources = new ShareResources();
        while (true) {
            new Thread(()->{
                shareResources.printAA();
            }, "线程A").start();

            new Thread(()->{
                shareResources.printBB();
            }, "线程B").start();

            new Thread(()->{
                shareResources.printCC();
            }, "线程C").start();
        }
    }
}
