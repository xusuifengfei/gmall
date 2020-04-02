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
enum CountryEnum{
    ONE(1,"齐"),TWO(2,"楚"),THREE(3,"燕"),FOUR(4,"赵"),FIVE(5,"魏"),SIX(6,"韩");
    private Integer retuInt;
    private String retuMessage;
    private CountryEnum(Integer retuInt ,String retuMessage){
        this.retuInt = retuInt;
        this.retuMessage = retuMessage;
    }

    public Integer getRetuInt() {
        return retuInt;
    }

    public String getRetuMessage() {
        return retuMessage;
    }

    public static CountryEnum forEach_CountryEnum(Integer retuInt){
        CountryEnum[] countryEnums = CountryEnum.values();
        for (CountryEnum countryEnum : countryEnums) {
            if (retuInt.equals(countryEnum.getRetuInt())){
                return countryEnum;
            }
        }
        return null;
    }
}
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
    @Test
    public void testAfter() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 1; i <= 6; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t"+"国被灭");
                latch.countDown();
            },CountryEnum.forEach_CountryEnum(i).getRetuMessage()).start();
        }
        latch.await();
        System.out.println(Thread.currentThread().getName()+"\t"+"秦国一统华夏");
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
