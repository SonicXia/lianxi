package com.sonic.syn;

/**
 * 线程不安全：取钱
 *
 * @author Sonic
 */
public class SynTest02 {
	public static void main(String[] args) {
		// 账户
		Account account = new Account(100, "礼金");
		SafeDrawing yui = new SafeDrawing("Yui", account, 60);
		SafeDrawing mio = new SafeDrawing("Mio", account, 80);

		yui.start();
		mio.start();
	}
}

class SafeDrawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney; // 取的钱数
	int packetTotal; // 口袋里的钱

	public SafeDrawing(String name, Account account, int drawingMoney) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
		test();
	}

	// 目标不对锁定失败，这里不是锁 this，应该锁 account
	public synchronized void test() {
		if (account.money - drawingMoney < 0) {
			return;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		account.money -= drawingMoney;
		packetTotal += drawingMoney;
		System.out.println(this.getName() + " --> " + "账户余额：" + account.money);
		System.out.println(this.getName() + " --> " + "口袋余额：" + packetTotal);

	}
}
