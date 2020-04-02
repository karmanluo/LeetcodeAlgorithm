package Test.BlockingQueue;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/2 15:49
 */
class MyThread implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"*****come in callable*****");
        return 1024;
    }
}
public class CallableDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyThread());

        new Thread(futureTask, "AAA").start();
        new Thread(futureTask, "BBB").start();

        int number = 100;
        System.out.println("****result: "+ (number+futureTask.get()));
    }
}
