package com.sonic.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket { // 资源类 = 实例变量 + 实例方法
    private int number = 30;
    Lock lock = new ReentrantLock();

    public void sale () {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t卖出第：" + number-- + "张票，还剩\t" + number + "张票");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo01 {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 匿名内部类的方式
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "B").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 40; i++) {
                    ticket.sale();
                }
            }
        }, "C").start();*/

        // lambda表达式的方式（推荐）
        new Thread(() -> {for (int i = 0; i < 40; i++) ticket.sale();}, "A").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) ticket.sale();}, "B").start();
        new Thread(() -> {for (int i = 0; i < 40; i++) ticket.sale();}, "C").start();
    }
}
