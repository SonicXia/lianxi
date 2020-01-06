package com.sonic.thread;

/**
 * @author Sonic
 */
public class AllState {

	public static void main(String[] args) {
		Thread t = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("...");
			}
		});

		// 观察状态
		Thread.State state = t.getState();
		System.out.println(state.toString()); // NEW

		t.start();
		state = t.getState();
		System.out.println(state.toString()); // RUNNABLE

		while (state != Thread.State.TERMINATED) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			state = t.getState();
			System.out.println("--" + state.toString()); // TIMED_WAITING
		}
		state = t.getState();
		System.out.println(state.toString()); // TERMINATED

	}


}
