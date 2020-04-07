package Test.JVM;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/4 22:28
 */

/**
 *      栈保存什么东西？
 *      8种基本的类型的变量，对象的引用变量，实例方法     都在在函数的栈内存中分配
 *
 *      java方法 = 栈帧（举例：main方法）
 */
public class JVMNote {
    //输入方法  输出方法   方法中的变量
    public  int add(int x, int y){
        int result = -1;    // int ---> 基本类型
        result = x + y;
        return result;
    }

    public static void m1(){
        m1();
    }

    public static void main(String[] args) {
        //Person p1 = new Person();   //Person--->引用类型
        System.out.println("111");
        m1();
        System.out.println("222");
    }
}
