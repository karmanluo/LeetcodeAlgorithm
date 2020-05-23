package Test.Java8;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/3 16:52
 */

//函数式接口专有注解，不加会隐式加上。
@FunctionalInterface
interface Foo {
    public int add(int x, int y);

    public default int mul(int x, int y){
        return x * y;
    }

    public static int dev(int x, int y){
        return x / y;
    }
}

/**
 *  1. 拷贝小括号，写死右箭头，落地大括号
 *  2. @FunctionalInterface 只能有一个普通方法；可以有多个默认（有default关键字）方法
 *  3.  default
 *  4.  static
 */
public class LambdaExpressionDemo01 {
    public static void main(String[] args) {
       /* Foo foo1 = new Foo() {
            @Override
        public int add(int x, int y) {
            return 0;
        }
    };*/
        //可以省略一大部分的代码
        Foo foo = (a, b) -> {return a + b;};
        System.out.println("调用add方法"+foo.add(1, 2));
        System.out.println("调用了default方法："+foo.mul(2, 4));
        System.out.println("调用了静态方法"+Foo.dev(10, 5));
    }
}
