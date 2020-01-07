package com.sonic.syn;

import java.util.ArrayList;
import java.util.List;

/**
 * 加入容器版
 * @author Sonic
 */
public class NewCinema {

	public static void main(String[] args) {
		// 可用的位置
		List<Integer> available = new ArrayList();
		available.add(1);
		available.add(2);
		available.add(3);
		available.add(4);
		available.add(5);

		// 顾客需要的位置
		List<Integer> seats1 = new ArrayList<>();
		seats1.add(1);
		seats1.add(2);
		List<Integer> seats2 = new ArrayList<>();
		seats2.add(4);
		seats2.add(5);
		seats2.add(7);

		HappyCinema c = new HappyCinema(available, "HappyCinema");
		new Thread(new HappyCustomer(c, seats1), "Sonic").start();
		new Thread(new HappyCustomer(c, seats2), "Konna").start();
	}

}

// 顾客
class HappyCustomer implements Runnable {
	HappyCinema cinema;
	List<Integer> seats;

	public HappyCustomer(HappyCinema cinema, List<Integer> seats) {
		this.cinema = cinema;
		this.seats = seats;
	}

	@Override
	public void run() {
		synchronized (cinema) {
			boolean flag = cinema.bookTickets(seats);
			if (flag) {
				System.out.println("出票成功" + Thread.currentThread().getName() +"-<位置为：" + seats);
			} else {
				System.out.println("出票失败" + Thread.currentThread().getName() +"-<位置不够");
			}
		}
	}
}

// 影院
class HappyCinema {
	List<Integer> available; // 可用的位置
	String name;

	public HappyCinema(List<Integer> available, String name) {
		this.available = available;
		this.name = name;
	}

	// 购票（具体位置）
	public boolean bookTickets(List<Integer> seats) {
		System.out.println("欢迎光临 " + this.name + "，可用位置为：" + available);
		List<Integer> copy = new ArrayList();
		copy.addAll(available);

		copy.removeAll(seats);
		// 判断大小
		if (available.size() - copy.size() != seats.size()) { // 失败
			return false;
		} else { // 成功
			available = copy;
			return true;
		}

	}

}
