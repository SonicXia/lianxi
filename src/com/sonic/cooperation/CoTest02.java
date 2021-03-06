package com.sonic.cooperation;

/**
 * 协作模型：生产者消费者实现方式一：信号灯法
 * 借助标志位
 *
 * @author Sonic
 */
public class CoTest02 {
	public static void main(String[] args) {
		Tv tv = new Tv();
		new Player(tv).start();
		new Watcher(tv).start();
	}
}

// 生产者 演员
class Player extends Thread{
	Tv tv;

	public Player(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			if (i % 2 == 0) {
				this.tv.play("奇葩说");
			} else {
				this.tv.play("太污了，喝瓶立白洗洗嘴");
			}
		}
	}
}

// 消费者 观众
class Watcher extends Thread{
	Tv tv;

	public Watcher(Tv tv) {
		this.tv = tv;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			tv.watch();
		}
	}
}

//同一个资源 电视
class Tv{
	String voice;
	//信号灯
	//true 表示演员表演，观众等待
	//false 表示演员等待，观众观看
	boolean flag = true;

	//表演
	public synchronized void play(String voice) {
		// 演员等待
		// 注意：在多线程的横向通信调用中，需要 Object.wait()的地方，要用 while判断而不是 if，防止在多线程环境下被虚假唤醒
		while (!flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 表演
		System.out.println("表演了：" + voice);
		this.voice = voice;
		// 唤醒
		this.notifyAll();
		// 切换标志
		this.flag = !this.flag;
	}

	// 观看
	public synchronized void watch() {
		// 观众等待
		// 注意：在多线程的横向通信调用中，需要 Object.wait()的地方，要用 while判断而不是 if，防止在多线程环境下被虚假唤醒
		while (flag) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("听到了：" + voice);
		// 唤醒
		this.notifyAll();
		// 切换标志
		this.flag = !this.flag;
	}
}
