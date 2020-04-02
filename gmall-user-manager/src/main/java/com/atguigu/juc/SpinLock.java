package com.atguigu.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 顾书才
 * @version V1.0
 * @Package com.atguigu.juc
 * @date 2020/4/2 0002 8:54
 * @Description
 */
public class SpinLock {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread  thread  = Thread.currentThread();

        while (!atomicReference.compareAndSet(null,thread)){
        }
    }
    public void myUnlock(){
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread ,null);
    }


    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        new Thread(()->{
            lock.myLock();
            System.out.println("thread name : "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.myUnlock();
        },"AA").start();

       TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            lock.myLock();
            System.out.println("thread name : "+Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.myUnlock();
        },"BB").start();
    }
}
