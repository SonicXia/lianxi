package com.sonic.interview.ref;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapDemo {

    public static void main(String[] args) {
        myHashMap();
        System.out.println("========================");
        myWeakHashMap();
    }

    private static void myHashMap() {
        Map<Integer, String> map = new HashMap<>();
        Integer key = new Integer(1);
        String value = "HashMap";

        map.put(key, value);
        System.out.println("初始        --> " + map);
        key = null;
        System.out.println("key = null  --> " + map);
        System.gc();
        System.out.println("System.gc() --> " + map + "\t" + map.size());
    }

    private static void myWeakHashMap() {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "WeakHashMap";

        weakHashMap.put(key, value);
        System.out.println("初始        --> " + weakHashMap);
        key = null;
        System.out.println("key = null  --> " + weakHashMap);
        System.gc();
        System.out.println("System.gc() --> " + weakHashMap + "\t" + weakHashMap.size());//GC后被回收
    }
}
