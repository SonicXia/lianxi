package com.sonic.interview.juc;

class Phone {
    public synchronized void sendSms() {
        System.out.println(Thread.currentThread().getName() + "\t sendSms");
        sendEmail();
    }
    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail");
    }
}

/**
 * 验证synchronized是可重入锁
 */
public class ReentrantLockDemo01 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {phone.sendSms();}, "AAA").start();
        new Thread(() -> {phone.sendSms();}, "BBB").start();
    }
}
