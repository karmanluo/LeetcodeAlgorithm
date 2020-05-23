package Test.JVM;

import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/5 17:34
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("配置的maxDirectBufferMemory："+(sun.misc.VM.maxDirectMemory()/(double)1024/1024)+"MB");
        try { TimeUnit.MILLISECONDS.sleep(3000); } catch (InterruptedException e) {  e.printStackTrace(); }
        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);

        //配置VM  -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
    }
}
