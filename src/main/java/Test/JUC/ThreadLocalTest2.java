package Test.JUC;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/7 15:40
 */
class Resource{
    private ThreadLocal<Integer> threadLocal;
    public Resource(ThreadLocal<Integer> threadLocal) {
        this.threadLocal = threadLocal;
        System.out.println(Thread.currentThread().getName()+"-->"+this.threadLocal.get());
        System.out.println("==========初始化完成==========");
    }

    public void set() {
        System.out.println(Thread.currentThread().getName()+"-->"+this.threadLocal.get());
        this.threadLocal.set(1);
        System.out.println(Thread.currentThread().getName()+"-->"+this.threadLocal.get());
    }
}
public class ThreadLocalTest2 {
    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->200);

    public static void main(String[] args) {
        Resource resource = new Resource(threadLocal);
        for (int i = 1; i <= 3; i++){
            new Thread(()->{
                resource.set();
            }, String.valueOf(i)).start();
        }
    }
}
