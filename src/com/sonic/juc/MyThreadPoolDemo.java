package com.sonic.juc;

import java.util.concurrent.*;

/**
 * 线程池的优势：
 *   线程池做的工作主要是控制运行的线程数量，
 *   处理过程中将任务放入队列，然后在线程创建后启动这些任务，
 *   如果线程数量超过了设定的数量，超出数量的线程排队等候，
 *   等其他线程执行完毕，再从队列中取出任务来执行。
 *
 * 主要特点：
 *   线程复用；控制最大并发数；管理线程。
 *
 * 1、降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁所造成的消耗；
 * 2、提高响应速度。当任务到达时，任务可以不需要等待线程创建就能立即执行；
 * 3、提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，
 *    还会降低系统的稳定性，使用线程池可以进行统一的分配、调优和管理。
 *
 * @author Sonic
 */
public class MyThreadPoolDemo {
	public static void main(String[] args) {
//		initPool();
		// CPU密集型：maximumPool的设定值要比计算机逻辑处理器的个数多1（即 Runtime.getRuntime().availableProcessors() + 1）
		// IO密集型：
		System.out.println(Runtime.getRuntime().availableProcessors());
		ExecutorService threadPool = new ThreadPoolExecutor(
				2,
				5,
				2L,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(3),// 缺省 Integer.MAX_VALUE
				Executors.defaultThreadFactory(),
				new ThreadPoolExecutor.AbortPolicy()
//				new ThreadPoolExecutor.CallerRunsPolicy()
//				new ThreadPoolExecutor.DiscardPolicy()
//				new ThreadPoolExecutor.DiscardOldestPolicy()
		);

		try {
			for (int i = 1; i <= 10; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t办理业务");
				});
				// 模拟给 CPU适当的程序处理时间，线程则会按一定的顺序执行
//				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放线程资源
			threadPool.shutdown();
		}

	}

	private static void initPool() {
		ExecutorService threadPool = Executors.newFixedThreadPool(5);// 固定线程数
//		ExecutorService threadPool = Executors.newSingleThreadExecutor();// 单线程
//		ExecutorService threadPool = Executors.newCachedThreadPool();// 自动扩展

		try {
			for (int i = 0; i < 10; i++) {
				threadPool.execute(() -> {
					System.out.println(Thread.currentThread().getName() + "\t办理业务");
				});
				// 模拟给 CPU适当的程序处理时间，线程则会按一定的顺序执行
				TimeUnit.SECONDS.sleep(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 释放线程资源
			threadPool.shutdown();
		}
	}


}
