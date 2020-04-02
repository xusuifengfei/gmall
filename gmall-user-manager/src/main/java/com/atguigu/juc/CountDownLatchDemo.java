package com.atguigu.juc;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @author 顾书才
 * @version V1.0
 * @Package com.atguigu.juc
 * @date 2020/4/2 0002 9:55
 * @Description 减少基数
 */
public class CountDownLatchDemo {

    @Test
    public void testBefore(){
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"号同学离开了教室");
            },""+i).start();
        }
        System.out.println(Thread.currentThread().getName()+"\t"+"班长锁教室门");
    }


    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i <= 5; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"号同学离开了教室");
                latch.countDown();
            },""+i).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+"\t"+"班长锁教室门");

    }
}
