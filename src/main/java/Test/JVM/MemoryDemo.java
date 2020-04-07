package Test.JVM;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/5 8:52
 */
public class MemoryDemo {
    public static void main(String[] args) {
        //int processors = Runtime.getRuntime().availableProcessors();  获得及其支持最大线程数
        long maxMemory = Runtime.getRuntime().maxMemory();      //返回java虚拟机试图使用最大内存量
        long totalMemory = Runtime.getRuntime().totalMemory();  //返回java虚拟机的内存总量
        System.out.println("-Xmx MAX_MEMORY"+maxMemory+"\t字节、"+(maxMemory/(double)1024/1024)+"MB");
        System.out.println("-Xms TOTAL_MEMORY"+totalMemory+"\t字节、"+(totalMemory/(double)1024/1024)+"MB");
    }
}
