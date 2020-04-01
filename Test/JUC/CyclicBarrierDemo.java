package Test.JUC;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @Author:KunmingLuo
 * @Date: 2020/4/1 22:58
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,
                ()->{System.out.println("集齐七颗龙珠，召唤神龙！"); });

        for (int i = 1; i <= 7; i++){
            final int tmpInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t收集到第："+tmpInt+"颗龙珠");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
