package com.sonic.juc;

/**
 * ThreadPoolExecutor 7大参数
 * @author Sonic
 */
public class SevenThreadPoolPerams {

	/**
	 * 1、corePoolSize：线程池中的常驻核心线程数；
	 * 2、maximumPoolSize：线程池中能够容纳同时执行的最大线程数，此值必须大于等于1（maximumPoolSize包含 corePoolSize）；
	 * 3、keepAliveTime：多余的空闲线程的存活时间。当前池中线程数量超过 corePoolSize时，
	 * 		当空闲时间达到 keepAliveTime时，多余线程会被销毁直到只剩下 corePoolSize个线程为止；
	 * 4、unit：keepAliveTime的单位；
	 * 5、workQueue：任务队列，被提交但尚未被执行的任务；
	 * 6、threadFactory：表示生成线程池中工作线程的线程工厂，用于创建线程，一般默认的即可；
	 * 7、handler：拒绝策略，表示当队列满了，并且工作线程大于等于线程池的最大线程数（maximumPoolSize）
	 * 		时如何来拒绝请求执行的 runnable的策略。
	 */

	/*
	public ThreadPoolExecutor(int corePoolSize,
							  int maximumPoolSize,
							  long keepAliveTime,
							  TimeUnit unit,
							  BlockingQueue<Runnable> workQueue,
							  ThreadFactory threadFactory,
							  RejectedExecutionHandler handler) {
		if (corePoolSize < 0 ||
				maximumPoolSize <= 0 ||
				maximumPoolSize < corePoolSize ||
				keepAliveTime < 0)
			throw new IllegalArgumentException();
		if (workQueue == null || threadFactory == null || handler == null)
			throw new NullPointerException();
		this.corePoolSize = corePoolSize;
		this.maximumPoolSize = maximumPoolSize;
		this.workQueue = workQueue;
		this.keepAliveTime = unit.toNanos(keepAliveTime);
		this.threadFactory = threadFactory;
		this.handler = handler;
	}
	*/


	/**
	 * 线程池底层工作原理
	 *
	 * 使用者：提交任务
	 *           |
	 * 线程池：核心线程池是否已满?-----否：创建线程执行任务
	 *                           |--是：队列是否已满?-----否：创建线程执行任务
	 *                                               |--是：最大线程池是否已满?-----否：创建线程执行任务
	 *                                                                         |--是：按照拒绝策略处理无法执行的任务
	 */

	/**
	 * 线程池底层工作原理（文字描述版）
	 *
	 * 1、在创建了线程池后，开始等待请求。
	 * 2、当调用 execute()添加一个请求任务时，线程池会做出如下判断：
	 *   2.1 如果正在运行的线程数量小于 corePoolSize，那么马上创建线程运行这个任务；
	 *   2.2 如果正在运行的线程数量大于或等于 corePoolSize，那么蒋政任务放入队列；
	 *   2.3 如果这个时候队列满了，且正在运行的线程数量还小于 maximumPoolSize，那么还是要创建非核心线程立即运行这个任务；
	 *   2.4 如果队列满了，且正在运行的线程数量大于或等于 maximumPoolSize，那么线程池会启动饱和拒绝策略来执行。
	 * 3、当一个线程完成任务时，它会从队列中取下一个任务来执行。
	 * 4、当一个线程无事可做，超过一定的时间（keepAliveTime）时，线程会判断：
	 *      如果当前运行的线程数大于 corePoolSize，那么这个线程就会被停掉。
	 *      所以，线程池的所有任务完成后，它最终会收缩到 corePoolSize的大小。
	 */

}
