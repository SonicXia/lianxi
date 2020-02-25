package com.sonic.interview.oom;

import java.util.concurrent.TimeUnit;

/**
 * 高并发下请求服务器时，经常出现如下异常
 * Java.lang.OutOfMemeoryError:unable to create new native thread
 * 准确地讲该native thread异常与对应的平台有关（线程操作与语言无关，与OS有关）
 *
 * 导致原因：
 * 1、应用创建了太多线程。一个应用进程创建多个线程，超过系统承载极限。
 * 2、服务器并不允许应用程序创建太多线程，linux系统默认允许单个进程可以创建的线程数是1024个，
 * 当超过这个数量，就会报此异常。
 *
 * 解决办法：
 * 1、降低应用程序擦护功能键的线程数量。分析应用是否真的需要创建大量的线程，如果不是，改代码将线程数量降到最低
 * 2、对于确实需要创建大量线程的应用，且数量超过linux系统默认1024个的限制，可以通过修改linux服务器配置，扩大默认限制
 */
public class UnableCreateNewThreadDemo {
    public static void main(String[] args) {
        for (int i = 0; ; i++) {
            System.out.println("******** i = " + i);
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, i + "").start();
        }
    }
}
