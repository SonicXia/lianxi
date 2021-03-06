package com.sonic.interview.juc;

import java.util.concurrent.*;

class HoldLockThread implements Runnable {
    private String lockA;
    private String lockB;

    public HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockA + "，尝试获得" + lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有" + lockB + "，尝试获得" + lockA);
            }
        }
    }
}

/**
 * 死锁是指两个或两个以上的进程在执行过程中，
 * 因争夺资源而造成的一种互相等待的现象，
 * 若无外力干涉，那他们都将无法推进下去
 */
public class DeadLockThreadDemo {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new HoldLockThread(lockA, lockB), "Thread-AAA").start();
        new Thread(new HoldLockThread(lockB, lockA), "Thread-BBB").start();

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2,
        3,
        10,
        TimeUnit.SECONDS,
        new LinkedBlockingQueue<Runnable>(8),
        Executors.defaultThreadFactory(),
        new ThreadPoolExecutor.AbortPolicy());

    }
}
