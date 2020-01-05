package com.sonic.thread;

public class Racer implements Runnable{
    private String winner;

    @Override
    public void run() {
        for (int steps = 1; steps <= 100; steps++) {
            // 模拟休息
            if (Thread.currentThread().getName().equals("rabbit") && steps % 30 == 0) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " --> " + steps);
            boolean flag = gameOver(steps);
            if (flag) {
                break;
            }
        }
    }

    private boolean gameOver(int steps) {
        if (winner != null) {
            return true;
        } else {
            if (steps == 100) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is " + winner);
                return true;
            } else {
                return false;
            }
        }
    }

    public static void main(String[] args) {
        // 共享资源
        Racer racer = new Racer();
        new Thread(racer, "tortoise").start();
        new Thread(racer, "rabbit").start();

    }

}
