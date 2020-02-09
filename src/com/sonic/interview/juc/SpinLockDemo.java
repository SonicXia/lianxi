package com.sonic.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 题目：实现一个自旋锁
 * 自旋锁好处：循环比较获取，直到成功为止，没有类似wait的阻塞。
 *
 * 通过CAS操作完成自旋锁，A线程先进来调用myLock()自己持有锁5s，
 * B随后进来发现当前线程持有锁不为null，所以只能通过自旋等待，
 * 直到A释放锁后，B随后抢到。
 */
public class SpinLockDemo {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();// 初始值为null

    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.currentThread().getName() + "\t invoke myLock");
        while (!atomicReference.compareAndSet(null, thread)) {
            // 自旋等待
        }
    }

    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.currentThread().getName() + "\t invoke myUnlock");
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) {
        SpinLockDemo spinLockDemo = new SpinLockDemo();
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t do something...");
                TimeUnit.SECONDS.sleep(5);} catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnlock();
        }, "AAA").start();
        // 保证线程A先获得锁
        try {TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
        new Thread(() -> {
            spinLockDemo.myLock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t do something...");
                TimeUnit.SECONDS.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
            spinLockDemo.myUnlock();
        }, "BBB").start();
    }
}
