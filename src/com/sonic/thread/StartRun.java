package com.sonic.thread;

/**
 * 创建线程方式二：
 * 1、创建：实现 Runable接口 + 重写 run
 * 2、启动：创建实现类对象 + Thread对象 + start
 *
 * 推荐：避免单继承的局限性，优先使用接口
 * 方便共享资源
 */
public class StartRun implements Runnable {
    /**
     * 线程入口点
     */
    @Override
    public void run() {
        for (int i = 0; i < 2000; i++) {
            System.out.println("一边听歌");
        }
    }

    public static void main(String[] args) {
//        // 创建实现类对象
//        StartRun sr = new StartRun();
//        // 创建代理类对象
//        Thread t = new Thread(sr);
//        // 启动
//        t.start(); // 会自动开启一个新的线程，同时程序继续往下执行，二者互不干扰（不保证会立即执行，由CPU调用）

        new Thread(new StartRun()).start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("一边coding");
        }
    }


}
