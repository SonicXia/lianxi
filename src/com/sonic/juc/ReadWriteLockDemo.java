package com.sonic.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {// 资源类
    private volatile Map<String, Object> map = new HashMap();// 保证可见性
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();// 加入可重入读写锁进行读写控制

    /**
     * 写
     * @param key
     * @param value
     */
    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();// 写锁
        System.out.println(Thread.currentThread().getName() + "\t---写入数据");
        // 模拟网络延时
        try {
            TimeUnit.SECONDS.sleep(1);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }

    /**
     * 读
     * @param key
     */
    public void get(String key) {
        readWriteLock.readLock().lock();// 读锁
        System.out.println(Thread.currentThread().getName() + "\t---读取数据");
        // 模拟网络延时
        try {
            TimeUnit.SECONDS.sleep(1);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成，结果为 " + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/**
 * 多个线程同时操作一个资源类没有任何问题，所以为了满足并发量，
 * 读取共享资源应该可以同时进行
 * 但是，如果有一个线程想去写共享资源，就不应该有其他线程可以对资源进行读或写
 *
 * 小总结:
 * 读-读 能共存
 * 读-写 不能共存
 * 写-写 不能共存
 * 写操作：原子+独占，整个过程必须是一个完整的统一整体，中间不允许被分割、被打断
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 1; i <= 3; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                myCache.put(tmpInt + "", tmpInt + "");
            }, String.valueOf(i)).start();
        }
        for (int i = 1; i <= 3; i++) {
            final int tmpInt = i;
            new Thread(() -> {
                myCache.get(tmpInt + "");
            }, String.valueOf(i)).start();
        }
    }
}

