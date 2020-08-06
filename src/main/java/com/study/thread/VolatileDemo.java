package com.study.thread;


import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * 1 验证volatile的可见性
 * 1.1 假如 int number = 0; number变量之前根本没有添加volatile关键字修饰，没有可见性
 * 1.2 添加了 volatile int number = 0; 可以解决可见性问题。
 *
 *  2 验证volatile不保证原子性
 *  2.1 原子性指的是什么意思？
 *      不可分割，完整性，也即某个线程正在做某个具体业务时，中间不可以被加塞或者被分割。需要整体完整，要么同时成功，要么同时失败。
 *
 * 2.2 volatile不保证原子性的案例演示
 *
 * 2.3 why
 *
 * 2.4  valatile不保证原子性问题解决,如何解决原子性？
 *      （1）加synchronized
 *       (2)使用我们的juc下AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {   //main是一切方法的入口
        MyData myData=new MyData();

        for (int i = 1; i <=20 ; i++) {
            new Thread(() -> {
                for (int j = 1; j <=1000 ; j++) {
                    myData.addPlusPlus();
                    myData.addMyAtomic();
                    
                }

            },String.valueOf(i)).start();

        }

        //需要等待上面20个线程都全部计算完成后，再用main线程取得的结果值是多少？
        while(Thread.activeCount() > 2){
            Thread.yield();
        }
        System.out.println(Thread.currentThread().getName()+"\t int finally number value:"+myData.number);
        System.out.println(Thread.currentThread().getName()+"\t AtomicInteger type, finally number value:"+myData.atomicInteger);

    }

    //volatile可以保证可见性，及时通知其他线程，主物理内存的值已经把修改。
    public static void seeOkByVolatile()

    {
        MyData myData = new MyData();  //资源类  //提取快捷键：ctrl+alt+M
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            myData.add();
            System.out.println(Thread.currentThread().getName() + "\t updated number value: " + myData.number);
        }, "AAA").start();

        // 第2个线程就是我们的main线程
        while (myData.number == 0) {
            // main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }

}


