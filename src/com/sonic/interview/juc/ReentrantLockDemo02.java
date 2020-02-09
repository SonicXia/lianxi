package com.sonic.interview.juc;

import java.util.concurrent.locks.ReentrantLock;

class Cellphone implements Runnable {
    ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        get();
    }
    private void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get");
            set();
        }finally {
            lock.unlock();
        }
    }
    private void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set");
        }finally {
            lock.unlock();
        }
    }
}
/**
 * 验证ReentrantLock是可重入锁
 */
public class ReentrantLockDemo02 {
    public static void main(String[] args) {
        Cellphone cellphone = new Cellphone();
        new Thread(cellphone, "AAA").start();
        new Thread(cellphone, "BBB").start();
    }
}
