package com.study.thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程使用方法有：
 * 第1种 继承Thread类
 * 第2种 实现runnable（）方法，没有返回值，不抛异常
 * 第3种  实现Callable（）方法，有返回值值，会抛异常
 */

/**
 * 第4种获得/使用java多线程的方式，线程池
 */
public class MyThreadPoolDemo {
    public static void main(String[] args) {

        System.out.println(Runtime.getRuntime().availableProcessors());  //查看CPU核数

        //线程池7大参数
        ExecutorService threadPool = new ThreadPoolExecutor(
                2, 5, 1L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());


        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
            }

        } catch (Exception e) {
            e.printStackTrace();


        }
    }


   public static void threadPoolInit() {
        ExecutorService threadPool1 = Executors.newFixedThreadPool(5); //一池5个处理线程
        ExecutorService threadPool2 = Executors.newSingleThreadExecutor(); //1池一个处理线程

        ExecutorService threadPool3 = Executors.newCachedThreadPool(); //1池N个处理线程
        //模拟10个用户来办理业务，每个用户就是一个就是来自外部的请求线程
        try {
            for (int i = 1; i <= 20; i++) {
                threadPool3.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                //暂停一会儿线程
                try {
                    TimeUnit.MICROSECONDS.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool3.shutdown();

        }
    }
}