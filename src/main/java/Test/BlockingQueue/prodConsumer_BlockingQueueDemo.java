package Test.BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 14:37
 * <p>
 * volatile/CAS/atomicInteger/BlockingQueue/线程交互/原子引用
 */
class MyResource {
    private volatile boolean FLAG = true; //默认开启生产+消费 模式
    private AtomicInteger atomicInteger = new AtomicInteger();

    //不指定阻塞队列的类型
    //高手都传接口，不传类======为了保证通用性
    BlockingQueue<String> blockingQueue = null;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println(blockingQueue.getClass().getName());
    }

    public void myPord() throws Exception {
        String data = null;
        boolean retValue;
        while (FLAG) {
            data = atomicInteger.getAndIncrement() + "";
            retValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (retValue) {
                System.out.println(Thread.currentThread().getName() + "\t插入数据：" + data + "成功！");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t插入数据：" + data + "失败！");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName()+"\t大老板叫停了，表示FLAG == false，生产动作结束");
    }

    public void myConsumer() throws Exception {
        String data = null;
        while (FLAG) {
            data = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if (null == data || data.equalsIgnoreCase("")){
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t超过两秒钟没有取到蛋糕，消费退出！");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费队列蛋糕 " + data + "成功！");
        }
    }

    public void stop(){
        this.FLAG = false;
    }
}

public class prodConsumer_BlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myPord();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "consumer").start();

        try { TimeUnit.SECONDS.sleep(5); } catch (InterruptedException e) {  e.printStackTrace(); }
        System.out.println();
        System.out.println(Thread.currentThread().getName()+"\t5秒钟时间到，主线程叫停");
        myResource.stop();
    }
}
