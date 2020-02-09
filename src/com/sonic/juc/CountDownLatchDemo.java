package com.sonic.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 减1计数器
 * CountDownLatch主要有两个方法（await()和countdown()），当一个或多个线程调用 await()时，这些线程会阻塞。
 * 其他线程调用 countdown()会将计数器减1（调用 countDown()的线程不会阻塞），
 * 当计数器的值变为0时，因 await()阻塞的线程会被唤醒，继续执行。
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws Exception {
        final int cnt = 5;
        CountDownLatch cdl = new CountDownLatch(cnt);
        for (int i = 1; i <= cnt; i++) {
            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + "\t离开教室");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    // 注意：countDown()必须要放在线程方法的最后执行，否则会出现主线程已经开始执行但其他线程还未执行结束的情况
                    // 所以可以放在 finally代码块中
                    cdl.countDown();
                }
            }, String.valueOf(i)).start();
        }
        cdl.await();
        System.out.println(Thread.currentThread().getName() + "\t班长离开教室");
    }
}


