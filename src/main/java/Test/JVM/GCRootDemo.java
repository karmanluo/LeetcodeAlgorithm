package Test.JVM;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/5 11:45
 *      在java中，作为GC Root 的对象有：
 *      1.虚拟机栈（栈帧中的本地变量表）引用的对象；
 *      2.方法区中静态属性引用的对象；
 *      3.方法区中常量引用的对象；
 *      4.本地方法栈JNI中引用的对象
 */
public class GCRootDemo {

    //private static GCRootDemo2 t2;  //方法区静态属性引用的对象
    //private static final GCRootDemo3 t3 = new GCRootDemo03(8);  //方法区静态属性引用的对象

    public static void m1(){
        GCRootDemo gcRootDemo = new GCRootDemo();   //虚拟机栈（栈帧中的本地变量表）引用的对象；
        System.gc();
        System.out.println("第一次完成GC");
    }

    public static void main(String[] args) {
        m1();
    }
}
