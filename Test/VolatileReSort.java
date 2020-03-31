package Test;

/**
 * @Author:KunmingLuo
 * @Date: 2020/3/31 10:54
 */

//禁止指令重排序案例
public class VolatileReSort {
    int a = 0;
    boolean flag = false;

    public void method1(){
        a = 1;          //语句1
        flag = true;    //语句2
    }

    public void method2(){
        if (flag){     //语句2执行才能进
            a = a + 5;
            System.out.println("*******retValue: " + a);
        }
    }
}
