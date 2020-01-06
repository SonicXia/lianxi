package com.sonic.thread;

/**
 * Lambda表达式简化线程（用一次）的使用
 */
public class LambdaThread {
    // 静态内部类
    static class Test implements Runnable{
        public void run() {
            for (int i = 0; i < 2000; i++) {
                System.out.println("一边听歌");
            }
        }
    }


    public static void main(String[] args) {

//        new Thread(new Test()).start();
        // 局部内部类
        class Test2 implements Runnable{
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    System.out.println("一边听歌");
                }
            }
        }
        new Thread(new Test2()).start();

        // 匿名内部类不许借助接口或者父类
        new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 2000; i++) {
                    System.out.println("一边听歌");
                }
            }
        }).start();

        // jdk8 简化 lambda
        new Thread(() -> {
            for (int i = 0; i < 2000; i++) {
                System.out.println("一边听歌");
            }
        }).start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("一边coding");
        }
    }


}
