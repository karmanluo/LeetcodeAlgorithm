package Test.JVM;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/4 0:26
 */
public class MyObject {
    public static void main(String[] args) {
        Object object = new Object();
        System.out.println(object.getClass().getClassLoader());
        System.out.println();

        MyObject myObject = new MyObject();
        System.out.println(myObject.getClass().getClassLoader().getParent().getParent());
        System.out.println(myObject.getClass().getClassLoader().getParent());
        System.out.println(myObject.getClass().getClassLoader());
        
        new Thread(()->{}, "aa").start();
    }
}
