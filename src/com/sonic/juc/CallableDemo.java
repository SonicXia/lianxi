package com.sonic.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Runnable{

    @Override
    public void run() {

    }
}
class MyThread2 implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"come in callable");
        return 200;
    }
}

public class CallableDemo {
    public static void main(String[] args) throws Exception {
        //FutureTask<Integer> futureTask = new FutureTask(new MyThread2());
        FutureTask<Integer> futureTask = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"  come in callable");
            TimeUnit.SECONDS.sleep(1);
            return 1024;
        });
        FutureTask<Integer> futureTask2 = new FutureTask(()->{
            System.out.println(Thread.currentThread().getName()+"  come in callable");
            TimeUnit.SECONDS.sleep(1);
            return 2048;
        });

        new Thread(futureTask,"zhang3").start();
        new Thread(futureTask2,"li4").start();

        //System.out.println(futureTask.get());
        //System.out.println(futureTask2.get());
        //1、一般放在程序后面，直接获取结果
        //2、只会计算结果一次

        while(!futureTask.isDone()){
            System.out.println("***wait");
        }
        System.out.println(futureTask.get());
        System.out.println(Thread.currentThread().getName()+" come over");
    }
}