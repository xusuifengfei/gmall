package com.atguigu.juc;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private Map<String,Object> map = new HashMap<>();
    private ReadWriteLock lock =  new ReentrantReadWriteLock();

    public void put(String key,Object object){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t"+"正在写入"+key);
         try {
                 TimeUnit.SECONDS.sleep(2);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        map.put(key,object);
        System.out.println(Thread.currentThread().getName()+"\t"+"写入完成");
        lock.writeLock().unlock();
    }
    public Object get(String key){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+"\t"+"正在读取"+key);
         try {
                 TimeUnit.SECONDS.sleep(1);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
        System.out.println(Thread.currentThread().getName()+"\t"+"读取完成");
        Object o = map.get(key);
        lock.readLock().unlock();
        return o;
    }
}



/**
 * @author 顾书才
 * @version V1.0
 * @Package com.atguigu.juc
 * @date 2020/4/2 0002 9:17
 * @Description
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                myCache.put(""+tempInt,tempInt);
            },""+i).start();
        }
        for (int i = 0; i < 5; i++) {
            final int tempInt = i;
            new Thread(()->{
                Object o = myCache.get("" + tempInt);
            },""+tempInt).start();
        }


    }

}
