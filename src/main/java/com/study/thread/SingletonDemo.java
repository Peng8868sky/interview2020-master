package com.study.thread;

/**
 * 这个方式单例模式出错率99.9%
 */
public class SingletonDemo {
    private static volatile SingletonDemo INSTANCE = null;

    public SingletonDemo() {
        System.out.println(Thread.currentThread().getName() + " new SingletonDemo()...");
    }

    public static  SingletonDemo getInstance() {
        if (INSTANCE == null) {
            synchronized (SingletonDemo.class){
                if(INSTANCE ==null){
                    INSTANCE =new SingletonDemo();
                }
            }
        }
        return INSTANCE;
    }

    public static void main(String[] args) {
         //并发多线程后，  情况发生了很大变化
        for (int i = 1; i < 10 ; i++) {
            new Thread(() ->{
                SingletonDemo.getInstance();
            },String.valueOf(i)).start();
            
        }
    }
}
