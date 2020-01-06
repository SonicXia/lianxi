package com.sonic.thread;

/**
 * 守护线程：是为用户线程服务的；jvm停止永不等待守护线程执行完毕
 * 默认：用户线程 jvm等待用户线程执行完毕才会停止
 * @author Sonic
 */
public class DeamonTest {
	public static void main(String[] args) {
		Thread t = new Thread(new God());
		t.setDaemon(true); // 设置守护线程
		t.start();
		new Thread(new Me()).start();
	}
}

class Me implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 365 * 100; i++) {
			System.out.println("happy life...");
		}
		System.out.println("ooooooooo");
	}
}

class God implements Runnable {
	@Override
	public void run() {
		for (;true;) {
			System.out.println("bless you...");
		}
	}
}