package com.sonic.syn;

/**
 * 线程安全：在并发时保证数据的准确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块（锁的对象一般为待修改的对象）
 *
 * @author Sonic
 */
public class SynBlockTest03 {

	public static void main(String[] args) {
		// 一份资源
		SynWeb12306 web = new SynWeb12306();
		// 多个代理
		new Thread(web, "Dxf").start();
		new Thread(web, "Yui").start();
		new Thread(web, "Mio").start();
	}
}

class SynWeb12306 implements Runnable {
	// 票数
	private int ticketNums = 10; // 有限的资源
	private boolean flag = true;
	@Override
	public void run() {
		while (flag) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
//			test1();
//			test2();
//			test3();
//			test4();
			test5();
		}
	}
	// 线程安全，设置同步方法，锁资源（ticketNums、flag）
	public synchronized void test1() {
		if (ticketNums <= 0) {
			flag = false;
			return;
		}
		// 模拟延时
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
				+ " --> " + ticketNums--);
	}

	// 线程安全，同步块（this对象不变，地址不变），范围太大性能效率低下
	public void test2() {
		// this对象即 SynWeb12306
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()
					+ " --> " + ticketNums--);
		}
	}

	// 线程不安全，锁定失败，ticketNums对象在变（地址在变）
	public void test3() {
		synchronized ((Integer)ticketNums) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()
					+ " --> " + ticketNums--);
		}
	}

	// 线程不安全，范围太小锁不住
	public void test4() {
		// this对象即 SynWeb12306
		synchronized (this) {
			if (ticketNums <= 0) {
				flag = false;
				return;
			}
		}
		// 模拟延时
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()
				+ " --> " + ticketNums--);
	}

	// 线程安全，尽可能锁定合理的范围（不是指代码，而是数据的完整性）
	// double checking
	public void test5() {
		if (ticketNums <= 0) { // 考虑的是没有票的情况
			flag = false;
			return;
		}
		// this对象即 SynWeb12306
		synchronized (this) {
			if (ticketNums <= 0) { // 考虑的是最后1张票的情况
				flag = false;
				return;
			}
			// 模拟延时
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()
					+ " --> " + ticketNums--);
		}

	}

}
