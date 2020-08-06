package com.study.thread;


import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache{  //资源类
    private volatile Map<String,Object>map=new HashMap<>();
    private ReentrantReadWriteLock readWriteLock =new ReentrantReadWriteLock();

    //写
    public void put(String key,Object value){

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"\t 正在写入:"+key);

            try {
                TimeUnit.MICROSECONDS.sleep(300);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"\t写入完成:");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }



    }

    //读
    public void get(String key) {

        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取:" );

            //暂停一会儿线程
            try {
                TimeUnit.MICROSECONDS.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成:" + result);
        }catch (Exception e){
    }finally {
            readWriteLock.readLock().unlock();
        }

    }


}

/**
 * 2020/7/17  20：00
 * Java锁之读写锁代码验证
 *
 * 多个线程同时读一个资源类没有任何问题，素所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是，如果有一个线程想要写共享资源来，就不应该再有其他线程可以对该线程进行读或写
 *
 * 小总结：
 * 读-    读能共存
 * 读-    读不能共享
 * 写-   写不能共存
 *
 * 写操作：原子+独占, 整个过程必须是一个完整的统一体，中间不许被分割，被打断
 *
 */
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache= new MyCache();

        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                myCache.put(tempInt+"",tempInt+"");
            },String.valueOf(i)).start();
            
        }

        for (int i = 1; i <=5 ; i++) {
            final int tempInt=i;
            new Thread(()->{
                myCache.get(tempInt+"");
            },String.valueOf(i)).start();

        }
    }


}
