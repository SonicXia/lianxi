package com.sonic.syn;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全：在并发时保证数据的准确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块（锁的对象一般为待修改的对象）
 *
 * @author Sonic
 */
public class SynContainer {
	public static void main(String[] args) {
		CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
		for (int i = 0; i < 10000; i++) {
			new Thread(() -> {
//				// 同步块（引用 CopyOnWriteArrayList，取消同步块，内部已实现同步块）
//				synchronized (list) {
					list.add(Thread.currentThread().getName());
//				}
			}).start();
		}
		try {
			// 主线程等待线程处理完
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(list.size());
	}

}
