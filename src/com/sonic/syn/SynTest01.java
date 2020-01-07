package com.sonic.syn;

/**
 * 线程安全：在并发时保证数据的准确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块（锁的对象一般为待修改的对象）
 *
 * @author Sonic
 */
public class SynTest01 {

	public static void main(String[] args) {
		// 一份资源
		SafeWeb12306 web = new SafeWeb12306();
		// 多个代理
		new Thread(web, "Dxf").start();
		new Thread(web, "Yui").start();
		new Thread(web, "Mio").start();
	}
}

class SafeWeb12306 implements Runnable {
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
			test();
		}
	}
	// 线程安全，设置同步方法，锁资源（ticketNums、flag）
	public synchronized void test() {
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
