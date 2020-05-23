package Test.多线程;

import java.util.concurrent.TimeUnit;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/3 19:41
 */
class Phone{
    public static synchronized void sendEmail(){
        try { TimeUnit.SECONDS.sleep(4); } catch (InterruptedException e) {  e.printStackTrace(); }
        System.out.println(Thread.currentThread().getName()
                +"\t*********sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println(Thread.currentThread().getName()
                +"\t*********sendSMS");
    }

    public void sayHello(){
        System.out.println(Thread.currentThread().getName()
                +"\t*********sayHello");
    }
}
public class Synchronized_8锁Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();
        new Thread(()->{
            phone.sendEmail();
        }, "AA").start();

        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) {  e.printStackTrace(); }

        new Thread(()->{
            //phone.sendSMS();
            phone2.sendSMS();
            //phone.sayHello();
        }, "BB").start();

    }
}
