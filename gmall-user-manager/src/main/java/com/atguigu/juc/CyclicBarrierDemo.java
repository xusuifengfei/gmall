package com.atguigu.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author 顾书才
 * @version V1.0
 * @Package com.atguigu.juc
 * @date 2020/4/2 0002 10:31
 * @Description 循环栅栏 集齐七颗龙珠，召唤神龙
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(7, () -> {
            System.out.println("集齐7颗龙珠，召唤神龙");
        });
        for (int i = 1; i <8 ; i++) {
            final int tempInt = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"第"+tempInt+"颗龙珠被收集到");
                try {
                    barrier.await();
                } catch (InterruptedException e) {e.printStackTrace();

                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },""+i).start();

        }
    }
}
