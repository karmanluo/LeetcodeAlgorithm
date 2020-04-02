package Java知识点.三个常用的并发工具类.Semaphore计数信号量;

import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 * 它可以设定一个阈值，基于此，多个线程竞争获取许可信号，做自己的申请后归还，
 * 超过阈值后，线程申请许可信号将会被阻塞。
 *
 * Semaphore可以用来构建一些对象池，资源池之类的，比如数据库连接池，我们也可以
 * 创建计数为1的Semaphore，将其作为一种类似互斥锁的机制，这也叫二元信号量，表
 * 示两种互斥状态。它的用法如下：
 *          availablePermits函数用来获取当前可用的资源数量
 *          wc.acquire(); //申请资源
 *          wc.release();// 释放资源
 *
 * 10个人去大保健，但是只有三个小姐姐
 */
class UserThread extends Thread{
    private String name;
    private Semaphore jsShop;

    public UserThread(String name, Semaphore jsShop){
        this.name = name;
        this.jsShop = jsShop;
    }

    @Override
    public void run() {
        //剩下的技师
        int availablePermits = jsShop.availablePermits();
        if (availablePermits > 0){
            System.out.println(name + " 找到小姐姐按摩了。。。");
        }else {
            System.out.println(name + " 再等会~~~~~~~~~~~呗~~~~~");
        }
        try {
            //申请技师
            jsShop.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + "  开始按摩" + ", 剩下的技师数目："+ jsShop.availablePermits());

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " 按摩完了。。。。");

        //释放技师
        jsShop.release();
    }
}

public class SemaphoreTest {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 1; i <= 10; i++) {
            UserThread userThread = new UserThread("第 " + i + "个人", semaphore);
            userThread.start();
        }
    }
}

