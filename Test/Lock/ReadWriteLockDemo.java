package Test.Lock;

import sun.misc.Cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/1 21:42
 */
class MyCache{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void put(String key, Object value){
        rwLock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t正在写入："+key);
            map.put(key,value);
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {  e.printStackTrace(); }
            System.out.println(Thread.currentThread().getName()+"\t写入完成：");
         }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.writeLock().unlock();
        }
    }

    public void get(String key){
        rwLock.readLock().lock();
        try{
            System.out.println(Thread.currentThread().getName()+"\t正在读取：");
            try { TimeUnit.MILLISECONDS.sleep(300); } catch (InterruptedException e) {  e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName()+"\t读取完成："+result);
         }catch (Exception e){
            e.printStackTrace();
        }finally {
            rwLock.readLock().unlock();
        }
    }
}

public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache cache = new MyCache();

        for (int i = 1; i <= 3; i++){
            final int tempInt = i;
            new Thread(()->{
                cache.put(tempInt+"", tempInt+"");
            }, String.valueOf(i)).start();
        }

        for (int i = 1; i <= 3; i++){
            final int tempInt = i;
            new Thread(()->{
                cache.get(tempInt+"");
            }, String.valueOf(i)).start();
        }
    }
}
