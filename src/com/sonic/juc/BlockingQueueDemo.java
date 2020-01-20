package com.sonic.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Sonic
 */
public class BlockingQueueDemo {
	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

		/**
		 * 1、抛出异常型 Throws exception
		 * NoSuchElementException
		 */
		/*System.out.println(blockingQueue.add("a"));// true
		System.out.println(blockingQueue.add("b"));// true
		System.out.println(blockingQueue.add("c"));// true
//		System.out.println(blockingQueue.add("x"));// NoSuchElementException
		System.out.println(blockingQueue.element());// a
		System.out.println(blockingQueue.remove());// a
		System.out.println(blockingQueue.remove());// b
		System.out.println(blockingQueue.remove());// c
//		System.out.println(blockingQueue.remove());// NoSuchElementException
*/
		/**
		 * 2、特殊值型 Special value
		 * false / null
		 */
		/*System.out.println(blockingQueue.offer("a"));// true
		System.out.println(blockingQueue.offer("b"));// true
		System.out.println(blockingQueue.offer("c"));// true
		System.out.println(blockingQueue.offer("x"));// false
		System.out.println(blockingQueue.peek());// a
		System.out.println(blockingQueue.poll());// a
		System.out.println(blockingQueue.poll());// b
		System.out.println(blockingQueue.poll());// c
		System.out.println(blockingQueue.poll());// null
*/

		/**
		 * 3、阻塞型 Blocks
		 * 当前线程阻塞，程序不结束
		 */
		/*blockingQueue.put("a");
		blockingQueue.put("b");
		blockingQueue.put("c");
//		blockingQueue.put("x");
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
		System.out.println(blockingQueue.take());
//		System.out.println(blockingQueue.take());
*/

		/**
		 * 超时型 Times out
		 */
		System.out.println(blockingQueue.offer("a",2, TimeUnit.SECONDS));// true
		System.out.println(blockingQueue.offer("b",2, TimeUnit.SECONDS));// true
		System.out.println(blockingQueue.offer("c",2, TimeUnit.SECONDS));// true
//		System.out.println(blockingQueue.offer("x",2, TimeUnit.SECONDS));// 等待2秒后，false
		System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));// a
		System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));// b
		System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));// c
		System.out.println(blockingQueue.poll(2, TimeUnit.SECONDS));// 等待2秒后，null


	}

}
