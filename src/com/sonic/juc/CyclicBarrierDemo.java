package com.sonic.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 加1计数器，与 CountDownLatch的逻辑正好相反
 */
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        final int cnt = 7;
        // CyclicBarrier(int parties, Runnable barrierAction)
        CyclicBarrier cb = new CyclicBarrier(cnt, () -> {
            System.out.println("**** 召唤神龙 ****");
        });
        for (int i = 1; i <= 7 ; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                System.out.println("收集到第" + tmpInt + "颗龙珠");
                try {
                    cb.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, String.valueOf(i)).start();
        }
    }
}
