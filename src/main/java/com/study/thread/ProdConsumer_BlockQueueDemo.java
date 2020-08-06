package com.study.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


class MyResource {

    private volatile boolean FLAG = true;  //默认开启，进行生产消费

    private AtomicInteger atomicInteger = new AtomicInteger();

    private BlockingQueue<String> blockingQueue = null;

    //BlockingQueue的构造方法
    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;

        //传反射
        System.out.println(blockingQueue.getClass().getName());
    }

    //阻塞队列传数据（生产数据）
    public void myProd() throws Exception {
        String data = null;
        boolean returnValue;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            returnValue = blockingQueue.offer(data, 2L, TimeUnit.SECONDS);
            if (returnValue) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列数据" + data + "失败");
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大老板叫听了，表示FLAG=false，生产动作结束" + FLAG);
    }

    //阻塞队列传数据（消费数据）
    public void myConsumer() throws Exception {
        String result = null;
        while (FLAG) { //消费与生产配对
            result = blockingQueue.poll(2L, TimeUnit.SECONDS);
            if(null==result||"".equalsIgnoreCase(result)){
                FLAG=false;
                System.out.println(Thread.currentThread().getName()+"\t"+"超过2秒钟没有取到蛋糕，消费退出");
                System.out.println();
                System.out.println();
                return;
            }
            System.out.println(Thread.currentThread().getName() + "消费队列" + result + "成功");

        }
    }
    public void stop() throws Exception{
        FLAG=false;
    }
}

/**
 * 2020/7/18 20:40
 *
 *volatile/CAS/AtomicInteger/BlockingQueue/线程交互/原子引用
 */
public class ProdConsumer_BlockQueueDemo{
    public static void main(String[] args) throws Exception {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<>(10));
        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t生产线程启动");
            try {
                myResource.myProd();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"Prod").start();

        new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"\t消费线程启动");
            try {
                myResource.myConsumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        },"consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("5秒钟时间到，大老板main线程叫停，活动结束");
        myResource.stop();
    }
}
