package com.sonic.interview.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    private static AtomicStampedReference<Integer> atomicStampedReference =
            new AtomicStampedReference<>(100, 1);
    public static void main(String[] args) {
        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +
                    " 的版本号为 " + stamp);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedReference.compareAndSet(
                    100, 101,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(
                    101, 100,
                    atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
        }, "AAA").start();

        new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            System.out.println(Thread.currentThread().getName() +
                    " 的版本号为 " + stamp);
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isSuccess = atomicStampedReference.compareAndSet(
                    100, 2020,
                    stamp, stamp + 1);
            System.out.println(isSuccess);
            System.out.println(atomicStampedReference.getReference());
        }, "BBB").start();
    }
}
