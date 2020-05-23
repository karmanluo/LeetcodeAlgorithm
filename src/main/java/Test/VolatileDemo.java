package Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/31 8:57
 */
class MyData {
    volatile int number = 0;//为了方便，设为public，没写get set

    public void addTO60() {
        this.number = 60;
    }

    public void addOne(){
        number++;
    }

    AtomicInteger atomicInteger = new AtomicInteger();
    public void addMyAtomicInteger(){
        atomicInteger.getAndIncrement();
    }
}
/**
 * 1.验证volatile的可见性；number变量在之前根本没有添加volatile关键字
 *         1.1 假如int number = 0；number变量之前根本没有添加volatile关键字,while 循环一直进行
 *         1.2 加了volatile之后；main线程可以获取到更改后的值
 * 2.验证volatile不保证原子性
 *      2.1 原子性指的是什么意思？不可分割，完整性，指正在做某个具体业务时，中间不可以被加塞，要么同时成功
 *      要么同时失败
 *
 */
public class VolatileDemo {
    //演示 volatile 不保证原子性的案例示例
    public static void main(String[] args) {
        MyData myData = new MyData();

        for (int i = 1; i <= 20; i++) {
            new Thread(()->{
                for (int j = 1; j <= 1000; j++) {
                    myData.addOne();
                    myData.addMyAtomicInteger();
                }
            }, String.valueOf(i)).start();
        }

        //上面20个线程都完成后，再用main线程获取到最终的结果值
        while (Thread.activeCount() > 2){   //在idea中main和main的监视线程就占了2个了
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t volatile int,finally number value: "+ myData.number);
        System.out.println(Thread.currentThread().getName() + "\t atomicInteger,finally number value: "+ myData.atomicInteger);
    }

    public static void seeOkByVolatile(){
        MyData mydata = new MyData();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            //暂停线程让别的线程也读取到变量
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mydata.addTO60();
            System.out.println(Thread.currentThread().getName()+"\t update number value： " + mydata.number);
        }, "AAA").start();

        while(mydata.number == 0){
            //主线程中值只要还是0，就一直循环
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over，main get number value："+mydata.number);

    }
}