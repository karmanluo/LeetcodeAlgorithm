package Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/31 15:50
 */
public class CasDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2019) + "\t current data: "+atomicInteger.get());
        System.out.println(atomicInteger.compareAndSet(5, 2014) + "\t current data: "+atomicInteger.get());
        atomicInteger.getAndIncrement();
    }
}
