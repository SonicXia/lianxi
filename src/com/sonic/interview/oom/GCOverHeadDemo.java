package com.sonic.interview.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 * GC回收时间过长时会抛出OutOfMemoryError，过长的定义是，
 * 超过98%的时间用来做GC并且回收了不到2%的堆内存。连续多次GC
 * 都只能回收不到2%的极端情况下才会抛出。
 * 假设不抛出GC overhead limit错误会发生什么情况呢？
 * 那就是GC清理的这么点内存会很快又被填满，迫使再次执行GC，这样就形成恶性循环，
 * CPU使用率一直都是100%，而GC却没有任何成果。
 */
public class GCOverHeadDemo {
    public static void main(String[] args) {
        int i = 0;
        List<Object> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("******* i：" + i);
            e.printStackTrace();
            throw e;
        }
    }
}
