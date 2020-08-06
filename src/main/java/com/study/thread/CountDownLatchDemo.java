package com.study.thread;

import com.study.enums.CountryEnum;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 国被灭");
                countDownLatch.countDown();
            }, CountryEnum.forEach_CountEnum(i).getRetMessage()).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*****************秦帝国，一统华夏");
        System.out.println();
        System.out.println(CountryEnum.ONE);
        System.out.println(CountryEnum.ONE.getRetCode());
        System.out.println(CountryEnum.ONE.getRetMessage());
    }

            






  public static void closeDoor() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 1; i <=6 ; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"\t 上晚自习，离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();

        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName()+"\t*****************班长最后关门走人");
    }
}
