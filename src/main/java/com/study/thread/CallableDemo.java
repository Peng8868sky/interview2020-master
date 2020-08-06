package com.study.thread;

        import java.util.concurrent.Callable;
        import java.util.concurrent.ExecutionException;
        import java.util.concurrent.FutureTask;
        import java.util.concurrent.TimeUnit;


class MyThread implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {

        System.out.println(Thread.currentThread().getName()+"**************** come in Callable");

        //暂停线程
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 1024;
    }
}

/**
 * 多线程，第3种 获得多线程的方式
 */
public class CallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //创建futuretask对象
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new MyThread());
      //  FutureTask<Integer> futureTask2 = new FutureTask<Integer>(new MyThread());

        new Thread(futureTask,"AA").start();
        new Thread(futureTask,"BB").start();
        //int result02=futureTask.get();

        System.out.println(Thread.currentThread().getName()+"*************************");
        int result01=100;


        int result02=futureTask.get();  //要求获得Callable线程的计算结果，如果没有计算完成就要去强求，会导致堵塞，值的计算完成
        System.out.println("******************result"+(result01+result02));

    }
}
