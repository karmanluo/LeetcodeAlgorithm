package Test.JUC;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal:每个线程自身的存储本地、局部区域
 *
 * @Author:KunmingLuo
 * @Date: 2020/4/7 15:19
 */
public class ThreadLocalTest1 {
    /*private static ThreadLocal<Integer> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            return 200;
        }
    };*/
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->200);

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
        threadLocal.set(99);
        System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());

        try { TimeUnit.SECONDS.sleep(2); } catch (InterruptedException e) {  e.printStackTrace(); }

        for (int i = 1; i <= 3; i++){
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
                threadLocal.set(100);
                System.out.println(Thread.currentThread().getName()+"-->"+threadLocal.get());
            }, String.valueOf(i)).start();
        }

    }
}
