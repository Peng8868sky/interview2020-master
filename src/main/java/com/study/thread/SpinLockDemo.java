package com.study.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * java锁之自旋锁代码验证
 */
public class SpinLockDemo {
    //原子引用线程
    AtomicReference<Thread>atomicReference = new AtomicReference<>();

    public void myLock(){
        Thread thread= Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"\t come in 0(_)0");

        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    public void myUnlock(){
        Thread thread=Thread.currentThread();
        atomicReference.compareAndSet(thread,null);
        System.out.println(Thread.currentThread().getName()+"\t invoked myUnlock()");
    }
    public static void main(String[] args) {
        SpinLockDemo spinLockDemo=new SpinLockDemo();
        new Thread(() ->{
            spinLockDemo.myLock();

            try {
                TimeUnit.SECONDS.sleep(5);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            spinLockDemo.myUnlock();
        },"AA").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
       new Thread(()->{
           spinLockDemo.myLock();

           try {
               TimeUnit.SECONDS.sleep(1);
           }catch (InterruptedException e){
               e.printStackTrace();
           }
           spinLockDemo.myUnlock();
       },"BB").start();

    }

}
