package Test.多线程;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/3 16:02
 */
class Ticket{
    private int ticket = 40;
    Lock lock = new ReentrantLock();

    public void sale(){
        lock.lock();
        try{
            if (ticket > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第"+ticket+"张，还剩下："+(--ticket)+"张票");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
public class SaleTicketDemo {
    public static void main(String[] args) {
        Ticket  ticket = new Ticket();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();    //调用.start()表示线程进入就绪状态，并不代表立即执行
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(()->{
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C").start();
    }
}
