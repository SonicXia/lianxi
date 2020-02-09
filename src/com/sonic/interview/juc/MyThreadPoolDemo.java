package com.sonic.interview.juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo {
    public static void main(String[] args) {
//        ExecutorService threadPool1 = Executors.newSingleThreadExecutor();//1池1个
//        ExecutorService threadPool2 = Executors.newFixedThreadPool(5);//1池5个
//        ExecutorService threadPool3 = Executors.newCachedThreadPool();//1池N个，[0,Integer.MAX_VALUE]

        ExecutorService threadPool = new ThreadPoolExecutor (
               2, 5, 1L, TimeUnit.SECONDS,
               new LinkedBlockingQueue<Runnable>(3),
               Executors.defaultThreadFactory(),
               new ThreadPoolExecutor.AbortPolicy()//默认抛出异常
//               new ThreadPoolExecutor.CallerRunsPolicy()
//               new ThreadPoolExecutor.DiscardPolicy()
//               new ThreadPoolExecutor.DiscardOldestPolicy()
        );
        // 模拟10个用户来办理业务，每个用户就是来自外部的请求线程
        // （因为maximumPoolSize + workQueue = 8 < 10，所以会有2个线程被拒绝策略拒绝）
        try {
            for (int i = 1; i <= 10; i++) {
                threadPool.execute(() -> {
                    try {
                        System.out.println(Thread.currentThread().getName() + "\t办理业务");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
