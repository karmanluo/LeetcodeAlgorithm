package Test.JVM;

import java.util.Random;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/5 9:29
 */
public class Memory1Demo {
    public static void main(String[] args) {
        String str = "memoryTest";
        while (true) {
            str = str + new Random().nextInt(8888888) + new Random().nextInt(999999999);
        }
    }
}
