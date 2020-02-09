package com.sonic.cooperation;

/**
 * 协作模型：生产者消费者实现方式一：管程法
 * 借助缓冲区
 *
 * @author Sonic
 */
public class CoTest01 {
	public static void main(String[] args) {
		SynContainer container = new SynContainer();
		new Productor(container).start();
		new Consumer(container).start();
	}
}

// 生产者
class Productor extends Thread {
	SynContainer container;

	public Productor(SynContainer container) {
		this.container = container;
	}

	@Override
	public void run() {
		// 生产
		for (int i = 0; i < 100; i++) {
			System.out.println("生产第-->" + i + " 个馒头");
			container.push(new Steamedbun(i));
		}
	}
}

// 消费者
class Consumer extends Thread {
	SynContainer container;

	public Consumer(SynContainer container) {
		this.container = container;
	}

	@Override
	public void run() {
		for (int i = 0; i < 100; i++) {
			System.out.println("消费第-->" + container.pop().id + " 个馒头");
		}
	}
}

// 缓冲区
class SynContainer {
	Steamedbun[] buns = new Steamedbun[10]; // 存储容器
	int count = 0; // 计数器
	// 储存 生产
	public synchronized void push(Steamedbun bun) {
		// 1、判断
		// 何时能生产？容器存在空间
		// 空间不足则不能生产
		// 注意：在多线程的横向通信调用中，需要 Object.wait()的地方，要用 while判断而不是 if，防止在多线程环境下被虚假唤醒
		while (count == buns.length) {
			try {
				this.wait(); // 线程阻塞，消费者通知生产则解除阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 2、干活
		// 存在空间则可以生产
		buns[count] = bun;
		count++;
		// 3、通知
		this.notifyAll(); // 存在数据，通知对方消费
	}
	// 获取 消费
	public synchronized Steamedbun pop() {
		// 1、判断
		// 何时消费，容器中是否存在数据
		// 没有数据只能等待（等待生产者生产好数据，再通知消费者[notifyAll()]）
		// 注意：在多线程的横向通信调用中，需要 Object.wait()的地方，要用 while判断而不是 if，防止在多线程环境下被虚假唤醒
		while (count == 0) {
			try {
				this.wait(); // 线程阻塞，生产者通知消费则解除阻塞
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 2、干活
		// 存在数据则可以消费
		count--;
		Steamedbun bun = buns[count];
		// 3、通知
		this.notifyAll(); // 存在空间，通知对方生产
		return bun;
	}
}

// 馒头
class Steamedbun {
	int id;

	public Steamedbun(int id) {
		this.id = id;
	}
}

