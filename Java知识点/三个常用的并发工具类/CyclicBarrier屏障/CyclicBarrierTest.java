package Java知识点.三个常用的并发工具类.CyclicBarrier屏障;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier初始化时同样也规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。
 * <p>
 * CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。
 * <p>
 * CyclicBarrier初始时还可带一个Runnable的参数， 此Runnable任务在CyclicBarrier的数目达到
 * 后，所有其它线程被唤醒前被执行。
 */
class Writer extends Thread {
    private CyclicBarrier cbr;

    public Writer(CyclicBarrier cbr) {
        this.cbr = cbr;
    }

    @Override
    public void run() {
        try {
            System.out.println("线程" + Thread.currentThread().getName() + ", 正在写入数据。。。");
            Thread.sleep(3000);
            System.out.println("线程" + Thread.currentThread().getName() + ", 数据写入成功。。。");
            cbr.await();
            System.out.println("所有线程都执行到 cbr.await() ---此时线程都执行完了");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}

public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            Writer writer = new Writer(cyclicBarrier);
            writer.start();
        }
    }
}
