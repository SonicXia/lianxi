package com.sonic.interview.juc;

class MyReentrantLock {
    boolean isLocked = false;
    Thread lockedBy = null;
    int lockedCount = 0;
    public synchronized void lock() {
        Thread thread = Thread.currentThread();
        while (isLocked && lockedBy != thread) {// 被其他线程锁着
            try {
                wait();// 等待
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }
    public synchronized void unlock() {
        Thread thread = Thread.currentThread();
        if (thread == lockedBy) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();// 唤醒等待的线程
            }
        }
    }
}

class Device01 {
    MyNotReentrantLock lock = new MyNotReentrantLock();
    public void sendSms() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t sendSms");
            sendEmail();
        } finally {
            lock.unlock();
        }
    }
    public void sendEmail() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t sendEmail");
        } finally {
            lock.unlock();
        }
    }
}

public class MyReentrantLockDemo {
    public static void main(String[] args) {
        Device02 device = new Device02();
        new Thread(() -> {device.sendSms();}, "AAA").start();
        new Thread(() -> {device.sendSms();}, "BBB").start();
    }
}
