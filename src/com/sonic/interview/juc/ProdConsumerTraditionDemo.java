package com.sonic.interview.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
    private int num = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (num != 0) {
                // 等待，不生产
                condition.await();
            }
            // 生产
            num++;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public void decrement() {
        lock.lock();
        try {
            while (num == 0) {
                // 等待，不消费
                condition.await();
            }
            // 消费
            num--;
            System.out.println(Thread.currentThread().getName() + "\t" + num);
            // 通知唤醒
            condition.signalAll();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumerTraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareData.increment();
            }
        }, "AAA").start();
        new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                shareData.decrement();
            }
        }, "BBB").start();
    }
}
