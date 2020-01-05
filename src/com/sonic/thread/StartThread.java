package com.sonic.thread;

/**
 * 创建线程方式一：
 * 1、创建：继承 Thread类 + 重写 run
 * 2、启动：创建子类对象 + start
 */
public class StartThread extends Thread {
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
        // 创建子类对象
        StartThread st = new StartThread();
        st.start(); // 会自动开启一个新的线程，同时程序继续往下执行，二者互不干扰（不保证会立即执行，由CPU调用）
//        st.run(); // 普通方法的调用，程序会执行完钙方法后再继续往下执行
        for (int i = 0; i < 2000; i++) {
            System.out.println("一边coding");
        }
    }


}
