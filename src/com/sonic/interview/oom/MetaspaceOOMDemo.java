package com.sonic.interview.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * JVM参数：
 * -XX:MetaspaceSize=8m -XX:MaxMetaspaceSize=8m
 *
 * Java8及之后的版本用元空间Metaspace代替永久代。
 *
 * Metaspace是方法区在HotSpot中的实现，它与永久代最大的区别在于：Metaspace并不在虚拟机内存中，
 * 而是在使用本地内存，也即在Java8中，class metadata被存储在Metaspace的native memory中。
 *
 * 元空间存储了以下信息：
 * 虚拟机加载的类信息
 * 常量池
 * 静态变量
 * 即时编译后的代码
 *
 * 模拟Metaspace空间溢出，我们不断生成类往元空间存，类占据的空间是会超过Metaspace指定的空间大小的
 */
public class MetaspaceOOMDemo {
    static class OOMDemo {};
    public static void main(String[] args) {
        int i = 0;
        try {
            while (true) {
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMDemo.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o, args);
                    }
                });
                enhancer.create();
            }
        } catch (Throwable e) {
            System.out.println("***** 多少次后发生了异常：" + i);
            e.printStackTrace();
        }
    }
}
