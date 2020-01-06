package com.sonic.thread;

/**
 * 静态代理设计模式
 * 可用于记录日志、监控内存
 * @author Sonic
 */
public class StaticProxy {
	public static void main(String[] args) {
		// happyMarry()在内部实现了自己的逻辑，我们只关注目标对象 target
		new WeddingCompany(new You()).happyMarry();
//		new Thread(new 线程对象).start();
	}
}

interface Marry {
	void happyMarry();
}

/**
 * 真实角色（目标角色）
 */
class You implements Marry {
	@Override
	public void happyMarry() {
		System.out.println("you and 嫦娥终于奔月了。。。");
	}
}

/**
 * 代理角色
 */
class WeddingCompany implements Marry {
	// 真实角色
	private Marry target;

	public WeddingCompany(Marry target) {
	 this.target = target;
	}

	/*
	包含了目标角色的 happyMarry()实现逻辑，类似重写 run()；
	除此之外，代理角色还会执行自己定义的 ready()、after();
	 */
	@Override
	public void happyMarry() {
		ready();
		this.target.happyMarry();
		after();
	}

	private void ready() {
		System.out.println("布置猪窝");
	}
	private void after() {
		System.out.println("闹玉兔");
	}

}
