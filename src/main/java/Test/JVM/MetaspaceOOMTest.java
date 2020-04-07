package Test.JVM;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/5 18:23
 */
public class MetaspaceOOMTest {
    static class OOMTest{}
    public static void main(String[] args) {
        int i = 0;

        try {
            while (true){
                i++;
                //Enhencer enhencer = new Enhancer();
            }
        }catch (Exception e){
            System.out.println("********多少次后发生了异常："+i);
            e.printStackTrace();
        }
    }
}
