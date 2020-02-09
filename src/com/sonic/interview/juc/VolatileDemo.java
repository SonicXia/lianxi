package com.sonic.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Doo {
    /*
    AAA	 come in
    AAA	 update number value:60
     */
//    int number = 0;

    /*
    AAA	 come in
    AAA	 update number value:60
    main	 mission is over
     */
    volatile int number = 0;

    AtomicInteger atomicInteger = new AtomicInteger();

    public void addTo60() {
        this.number = 60;
    }


    public void addPlusPlus() {
        this.number++;
    }

    public void addAtomic() {
        atomicInteger.getAndIncrement();
    }
}

/**
 * 1 验证volatile的可见性
 *   1.1 假如 int number = 0;， number变量之前根本没有添加volatile关键词修饰，没有可见性
 *   1.2 添加了volatile，可以解决可见性问题
 *
 * 2 验证volatile不保证原子性
 *   2.1 原子性是什么意思？
 *       不可分割，完整性，也即某个线程在做某个业务时，中间不可以被加塞，或者分割，需要整体完整，
 *       要么同时成功，要么同时失败。
 *   2.2 volatile不保证原子性的案例演示
 *   2.3 why
 *   2.4 如何解决原子性？
 *       2.4.1 加synchronized
 *       2.4.2 使用juc下AtomicInteger
 */
public class VolatileDemo {
    public static void main(String[] args) {
//        seeOkByVolatile();

        atomicByVolatile();
    }

    /**
     * volatile不保证原子性
     * 解决：可以加synchronized或者使用juc下的AtomicInteger
     */
    private static void atomicByVolatile() {
        Doo doo = new Doo();// 资源类
        for (int i = 1; i <= 20; i++) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; j++) {
                    doo.addPlusPlus();
                    doo.addAtomic();
                }
            }, String.valueOf(i)).start();
        }

        // 需要等待上面20个线程全都计算完成后，再用main线程取得最终的结果值是多少
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }

        System.out.println(Thread.currentThread().getName() + "\t volatile int type, finally number value:" + doo.number);
        System.out.println(Thread.currentThread().getName() + "\t AtomicInteger type, finally number value:" + doo.atomicInteger);
    }

    /**
     * volatile可以保证可见性，及时通知其他线程，主物理内存的值已经被其他线程修改
     */
    private static void seeOkByVolatile() {
        Doo doo = new Doo();// 资源类

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t come in");
            try {
                TimeUnit.SECONDS.sleep(2);
                doo.addTo60();
                System.out.println(Thread.currentThread().getName() + "\t update number value:" + doo.number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AAA").start();

        // 第二个线程就是我们的main主线程
        while (doo.number == 0) {
            // main线程就一直在这里等待循环，直到number值不再等于0
        }
        System.out.println(Thread.currentThread().getName() + "\t mission is over");
    }
}
