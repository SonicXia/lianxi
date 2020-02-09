package com.sonic.interview.jvm;

public class HelloGc {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("******HelloGc");
        Thread.sleep(Integer.MAX_VALUE);
//        long totalMemory = Runtime.getRuntime().totalMemory();//返回JVM的内存总量
//        long maxMemory = Runtime.getRuntime().maxMemory();//返回JVM试图使用的最大内存量
//        System.out.println("TOTAL_MEMORY(-Xms) = " + totalMemory + "(字节)、" + (totalMemory / (double)1024 / 1024) + "MB");
//        System.out.println("MAX_MEMORY(-Xms) = " + maxMemory + "(字节)、" + (maxMemory / (double)1024 / 1024) + "MB");
    }
}
