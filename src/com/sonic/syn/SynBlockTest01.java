package com.sonic.syn;

/**
 * 线程安全：在并发时保证数据的准确性、效率尽可能高
 * synchronized
 * 1、同步方法
 * 2、同步块（锁的对象一般为待修改的对象）
 *
 * @author Sonic
 */
public class SynBlockTest01 {
	public static void main(String[] args) {
		// 账户
		Account account = new Account(100, "礼金");
		SynDrawing yui = new SynDrawing("Yui", account, 60);
		SynDrawing mio = new SynDrawing("Mio", account, 80);

		yui.start();
		mio.start();
	}
}

// 模拟取款
class SynDrawing extends Thread {
	Account account; // 取钱的账户
	int drawingMoney; // 取的钱数
	int packetTotal; // 口袋里的钱

	public SynDrawing(String name, Account account, int drawingMoney) {
		super(name);
		this.account = account;
		this.drawingMoney = drawingMoney;
	}

	@Override
	public void run() {
		test();
	}

	// 目标锁定 account
	public void test() {
		// 提高性能
		if (account.money <= 0) {
			return;
		}
		// 线程安全，同步块
		synchronized (account) {
			if (account.money - drawingMoney < 0) {
				System.out.println(this.getName() + " --> " + "账户余额不足！");
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

}
