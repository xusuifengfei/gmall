package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author 顾书才
 * @version V1.0
 * @Package com.atguigu.juc
 * @date 2020/4/2 0002 10:46
 * @Description 信号灯，抢车位
 */
public class SemaphoreDemo {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3); //模拟有3个车位
        for (int i = 1; i <= 6; i++) { //模拟6个车子抢车位
            new Thread(()->{
                try {
                    semaphore.acquire();  //抢占车位
                    System.out.println(Thread.currentThread().getName()+"\t"+"抢到了车位");
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(Thread.currentThread().getName()+"\t"+"离开了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally{
                    semaphore.release();
                }
            },""+i).start();
        }
    }
}
