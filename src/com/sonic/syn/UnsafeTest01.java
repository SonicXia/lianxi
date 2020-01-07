package com.sonic.syn;

/**
 * 线程不安全：数据有负数、相同
 *
 * @author Sonic
 */
public class UnsafeTest01 {

	public static void main(String[] args) {
		// 一份资源
		UnsafeWeb12306 web = new UnsafeWeb12306();
		// 多个代理
		new Thread(web, "Dxf").start();
		new Thread(web, "Yui").start();
		new Thread(web, "Mio").start();
	}
}

class UnsafeWeb12306 implements Runnable {
	// 票数
	private int ticketNums = 10; // 有限的资源
	private boolean flag = true;
	@Override
	public void run() {
		while (flag) {
			test();
		}
	}
	public void test() {
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
