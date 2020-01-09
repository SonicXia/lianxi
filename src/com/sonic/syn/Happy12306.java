package com.sonic.syn;

/**
 * @author Sonic
 */
public class Happy12306 {
	public static void main(String[] args) {
		Web12306 w = new Web12306(4, "happy web12306");
		new Passenger(w, "Sonic",2).start();
		new Passenger(w, "Konna",3).start();
	}
}

// 顾客（代理）
class Passenger extends Thread {
	int seats;
	public Passenger(Runnable target, String name, int seats) {
		super(target, name);
		this.seats = seats;
	}
}

// 火车票网
class Web12306 implements Runnable {
	int available; // 可用的位置
	String name; // 名称

	public Web12306(int available, String name) {
		this.available = available;
		this.name = name;
	}

	@Override
	public void run() {
		Passenger passenger = (Passenger) Thread.currentThread();
		boolean flag = this.bookTickets(passenger.seats);
		if (flag) {
			System.out.println("出票成功" + Thread.currentThread().getName() +"-<位置为：" + passenger.seats);
		} else {
			System.out.println("出票失败" + Thread.currentThread().getName() +"-<位置不够");
		}
	}
	// 购票
	public synchronized boolean bookTickets(int seats) {
		System.out.println("可用位置为：" + available);
		if (seats > available) {
			return false;
		}
		available -= seats;
		return true;
	}

}


