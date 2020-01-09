package com.sonic.others;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 不可重入锁
 *
 * @author Sonic
 */
public class LockTest03 {
	Relock lock = new Relock();
	public void a() throws InterruptedException {
		lock.lock();
		System.out.println("1-->" + lock.getHoldCount());
		doSomething();
		lock.unlock();
		System.out.println("4-->" + lock.getHoldCount());
	}

	// 不可重入
	public void doSomething() throws InterruptedException {
		lock.lock();
		System.out.println("2-->" + lock.getHoldCount());
		//.....
		lock.unlock();
		System.out.println("3-->" + lock.getHoldCount());
	}

	public static void main(String[] args) throws InterruptedException, UnknownHostException {
		LockTest03 test = new LockTest03();
		test.a();

		Thread.sleep(1000);
		System.out.println("5-->" + test.lock.getHoldCount());
	}

}

// 可重入锁
class Relock{
	// 是否占用
	private boolean isLocked = false;
	private Thread lockedBy = null;//存储线程
	private int holdCount = 0;
	// 使用锁
	public synchronized void lock() throws InterruptedException {
		Thread t = Thread.currentThread();
		while (isLocked && lockedBy != t) {
			wait();
		}
		isLocked = true;
		lockedBy = t;
		holdCount++;
	}
	// 释放锁
	public synchronized void unlock() {
		if (Thread.currentThread() == lockedBy) {
			holdCount--;
			if (holdCount == 0) {
				isLocked = false;
				notify();
				lockedBy = null;
			}
		}
	}

	public int getHoldCount() {
		return holdCount;
	}

}
