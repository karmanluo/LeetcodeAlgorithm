package Test;

import Java知识点.Static的用法.静态内部类实现单例模式.Singleton;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/31 14:16
 */
 public class SingletonDemo {
    private static SingletonDemo instance = null;

    private SingletonDemo(){
        System.out.println(Thread.currentThread().getName()+"\t 我是构造方法SingletonDemo()");
    }

    public static SingletonDemo getInstance(){
        if (instance == null){
            synchronized (SingletonDemo.class){
                if (instance == null){
                    instance = new SingletonDemo();
                }
            }
        }

        return instance;
    }

    public static void main(String[] args) {
 /*       System.out.println("--------------单线程--------------");
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
        System.out.println(SingletonDemo.getInstance() == SingletonDemo.getInstance());
*/
        System.out.println("--------------多线程--------------");
        //多线程后情况发生了很大的变化
        for (int i = 1; i <= 10000; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }, String.valueOf(i)).start();
        }
    }
}
