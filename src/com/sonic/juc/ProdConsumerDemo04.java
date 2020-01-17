package com.sonic.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Aircondition {
	private int number = 0;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	// jdk5的写法
	/*public synchronized void increment() throws Exception {
		// 1、判断
		while (number != 0) { // 不用 if，防止虚假唤醒
			this.wait();
		}
		// 2、干活
		number++;
		System.out.println(Thread.currentThread().getName() + "\t" + number);
		// 3、通知
		this.notifyAll();
	}*/
	// jdk8的写法（推荐）
	// synchronized -> lock;
	// Object.wait() -> Condition.await();
	// Object.notifyAll() -> Condition.signalAll()
	public void increment() throws Exception {
		lock.lock();
		try {
			// 1、判断
			while (number != 0) { // 不用 if，防止虚假唤醒
				condition.await(); // this.wait();
			}
			// 2、干活
			number++;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 3、通知
			condition.signalAll(); // this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	// jdk5的写法
	/*public synchronized void decrement() throws Exception {
		// 1、判断
		while (number == 0) { // 不用 if，防止虚假唤醒
			this.wait();
		}
		// 2、干活
		number--;
		System.out.println(Thread.currentThread().getName() + "\t" + number);
		// 3、通知
		this.notifyAll();
	}*/
	// jdk8的写法（推荐）
	// synchronized -> lock;
	// Object.wait() -> Condition.await();
	// Object.notifyAll() -> Condition.signalAll()
	public void decrement() throws Exception {
		lock.lock();
		try {
			// 1、判断
			while (number == 0) { // 不用 if，防止虚假唤醒
				condition.await(); // this.wait();
			}
			// 2、干活
			number--;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 3、通知
			condition.signalAll(); // this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 生产者消费者模式：类似管程法
 *
 * 题目：现在两个线程，可以操作初始值为0的一个变量
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为0
 *
 * 1、高聚低合前提下，线程操作资源类
 * 2、判断/干活/通知
 * 3、防止虚假唤醒
 *
 * @author Sonic
 */
public class ProdConsumerDemo04 {
	public static void main(String[] args) {
		Aircondition aircondition = new Aircondition();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					aircondition.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "A").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					aircondition.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "B").start();

		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					aircondition.increment();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "C").start();
		new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				try {
					aircondition.decrement();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "D").start();

	}

}
