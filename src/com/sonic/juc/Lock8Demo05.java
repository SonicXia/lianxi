package com.sonic.juc;

import java.util.concurrent.TimeUnit;

class Phone {
	public static synchronized void sendEmail() throws InterruptedException {
		TimeUnit.SECONDS.sleep(3);
		System.out.println("*****sendEmail");
	}
	public static synchronized void sendSMS() {
		System.out.println("*****sendSMS");
	}
	public void sayHello() {
		System.out.println("*****sayHello");
	}
}

/**
 * 8 lock
 *
 *1 标准访问，请问先打印邮件还是短信？[对象锁]（*****sendEmail*****sendSMS）
 *2 暂停4秒在邮件方法中，请问先打印邮件还是短信？[对象锁]（抱着资源睡，*****sendEmail*****sendSMS）
 *3 新增普通 sayHello方法，请问先打印邮件还是短信？[对象锁]（普通方法不加锁，各执行各的，互不影响）
 *4 两部手机，请问先打印邮件还是短信？[对象锁]（两个不同实例对象，各执行各的，互不影响）
 *5 两个静态同步方法，同一部手机，请问先打印邮件还是短信？[类锁]（*****sendEmail*****sendSMS）
 *6 两个静态同步方法，2部手机请问先打印邮件还是短信？[类锁]（*****sendEmail*****sendSMS）
 *7 1个静态同步方法，1个普通同步方法，同一部手机请问先打印邮件还是短信？（锁的对象不同[类锁、对象锁]。各执行各的，互不影响）
 *8 1个静态同步方法，1个普通同步方法，2部手机请问先打印邮件还是短信？（锁的对象不同[类锁、对象锁]。各执行各的，互不影响）
 *
 * 笔记：
 * (1~2)一个对象里面如果有多个 synchronized方法，某个时刻内，只要一个线程去调用其中的一个 synchronized方法了，
 * 		其他的线程都只能等待，换句话说，某一时刻内，只能有唯一一个线程去访问这些 synchronized方法。
 * 		锁的是当前对象 this，被锁定后，其他的线程都不能进入到当前对象的其他的 synchronized方法。
 * 		（synchronized方法只影响所在对象中的其他 synchronized方法）
 * (3)加个普通方法后发现和同步锁无关。
 * (4)换成两个对象后，不是同一把（对象）锁了，情况立刻变化。
 * (1~4)synchronized实现同步的基础：Java中的每一个对象都可以作为锁。
 * 		具体表现为以下三种形式：
 * 		1、对于普通同步方法，锁的是当前实例对象（this）；
 * 		2、对于同步方法块，锁的是 Synchronized括号里配置的对象；
 * (5~6)3、对于静态同步方法，锁的是当前类的 Class对象。
 *
 * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异常时必须释放锁。
 *
 * 也就是说，如果一个实例对象的非静态同步方法获取锁后，该实例对象的其他非静态同步方法必须等待获取锁
 * 的方法释放锁后才能获取锁，可是别的实例对象的非静态同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以无须等待该实例对象释放锁就可以获取它们自己的锁。
 *
 * 所有的静态同步方法用的也是用一把锁 --> 类对象本身（Class）
 * （7~8）这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 * 但是一旦一个静态同步方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁（锁的都是Class对象），
 * 不论是否是同一个实例对象的静态同步方法之间（phone1），
 * 还是不同的实例对象的静态同步方法之间（phone1与phone2），只要它们是同一个类（Class）的实例对象。
 *
 * this：当前对象锁
 * Class：全局锁（类锁）
 *
 * @author Sonic
 */
public class Lock8Demo05 {
	public static void main(String[] args) throws Exception {
		Phone phone = new Phone();
		Phone phone2 = new Phone();

		new Thread(() -> {
			try {
				phone.sendEmail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "A").start();

		TimeUnit.MILLISECONDS.sleep(100);

		new Thread(() -> {
			try {
//				phone.sendSMS();
//				phone.sayHello();
				phone2.sendSMS();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}, "B").start();

	}
}

