package com.study.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class MyData {
    // int number = 0;
    volatile int number = 0;  //volatile作为主线程可见性

    public void add() {
        this.number = 60;
    }

    //请注意，此时number前面是加了volatile关键字修饰的，volatile不保证原子性
    public   void addPlusPlus(){
        number++;
    }

    //valatile不保证原子性问题解决
   AtomicInteger atomicInteger=new AtomicInteger();
    public void addMyAtomic(){
        atomicInteger.getAndIncrement();
    }

}
