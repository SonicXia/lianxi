package com.sonic.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData {
	private String flag = "A";
	private int i = 1;
	private Lock lock = new ReentrantLock();
	private Condition c1 = lock.newCondition();
	private Condition c2 = lock.newCondition();
	private Condition c3 = lock.newCondition();

	public void print(int cnt) {
		lock.lock();
		try {
			if (cnt == 5) {
				while (!flag.equals("A")) {
					c1.await();
				}
				flag = "B";
				System.out.println("-------- 第" + i++ + "遍 --------");
				handlePrint(cnt);
				c2.signal();
			} else if (cnt == 10) {
				while (!flag.equals("B")) {
					c2.await();
				}
				flag = "C";
				handlePrint(cnt);
				c3.signal();
			} else if (cnt == 15) {
				while (!flag.equals("C")) {
					c3.await();
				}
				flag = "A";
				handlePrint(cnt);
				c1.signal();
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	private void handlePrint(int cnt) {
		for (int i = 1; i <= cnt ; i++) {
			System.out.println(Thread.currentThread().getName() + "\t" + i);
		}
	}
}

/**
 * 生产者消费者模式：信号灯法
 *
 * 多线程之间按顺序调用，实现 A -> B -> C
 * 三个线程启动，要求如下：
 *
 * A打印5次，B打印10次，C打印15次
 * 接着
 * A打印5次，B打印10次，C打印15次
 * ...
 * 来10轮
 *
 * @author Sonic
 */
public class ConditionDemo {
	public static void main(String[] args) {
		ShareData shareData = new ShareData();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareData.print(5);
			}
		}, "A").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareData.print(10);
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				shareData.print(15);
			}
		}, "C").start();
	}
}
