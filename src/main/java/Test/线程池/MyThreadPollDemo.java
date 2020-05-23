package Test.线程池;

import java.util.Collections;
import java.util.concurrent.*;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 16:59
 * <p>
 * 第一种：继承Thread类
 * 第二种：实现Runnable接口，没有返回值，不抛异常
 * 第三种：实现callable接口，有返回值，会抛异常
 * 第四种：获得/使用java多线程的方式，线程池
 */
public class MyThreadPollDemo {
    public static void main(String[] args) {
        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                1L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            //模拟10个用户办理业务
            for (int i = 1; i <= 8 ; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t办理业务");
                });
            }
        }catch (Exception e){
                e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
