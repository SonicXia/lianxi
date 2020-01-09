package com.sonic.others;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 任务调度
 *
 * @author Sonic
 */
public class TimerTask01 {
	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new MyTask(), 1000, 2000);
	}

}

// 任务类
class MyTask extends TimerTask {
	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("hello world-->" + i);
		}
	}
}