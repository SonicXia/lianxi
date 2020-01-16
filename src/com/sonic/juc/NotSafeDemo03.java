package com.sonic.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 1、故障现象
 * 	java.util.ConcurrentModificationException（并发修改异常）
 *
 * 2、导致原因
 *
 * 3、解决方法
 * 	3.1 new Vector(）;
 * 	3.2 Collections.synchronizedList(new ArrayList<>());
 * 	3.3 new CopyOnWriteArrayList<>();（写时复制，读写分离，juc包下，推荐）
 *
 * 4、优化建议
 *
 * @author Sonic
 */
public class NotSafeDemo03 {
	public static void main(String[] args) {
//		listNotSafe();
//		notSafeSet();
		notSafeMap();
	}

	private static void notSafeMap() {
		// new HashMap();
		Map<String, String> map = new ConcurrentHashMap<>();
		for (int i = 0; i < 1200; i++) {
			new Thread(() -> {
				map.put(Thread.currentThread().getName(),
						UUID.randomUUID().toString().substring(0, 8));
				System.out.println(map);
			}, String.valueOf(i)).start();
		}
	}

	private static void notSafeSet() {
		// new HashSet();
		Set<String> set = new CopyOnWriteArraySet<>();
		for (int i = 0; i < 1200; i++) {
			new Thread(() -> {
				set.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(set);
			}, String.valueOf(i)).start();
		}
	}

	private static void listNotSafe() {
		// new ArrayList(); // 会导致故障
		// new Vector();
		// Collections.synchronizedList(new ArrayList<>());
		List<String> list = new CopyOnWriteArrayList<>();
		for (int i = 0; i < 120; i++) {
			new Thread(() -> {
				list.add(UUID.randomUUID().toString().substring(0, 8));
				System.out.println(list);
			}, String.valueOf(i)).start();
		}
	}
}

/*
* 笔记
* 写时复制：
* CopyOnWrite容器即写时复制的容器。往一个容器添加元素的时候，不直接往当前容器 Object[]添加，
* 而是先将当前容器 Object[]进行copy，复制出一个新的容器 Object[] newElements，
* 然后新的容器 Object[] newElements里添加元素，添加完元素之后，再将原容器的的引用指向新的容器 setArray(newElements)。
* 这样做的好处是可以对 CopyOnWrite容器进行并发的读，而不需要加锁，因为当前容器不会添加任何元素。
* 所以 CopyOnWrite容器也是一种读写分离的思想，读和写不同的容器。
*
public boolean add(E e) {
	final ReentrantLock lock = this.lock;
	lock.lock();
	try {
		Object[] elements = getArray();
		int len = elements.length;
		Object[] newElements = Arrays.copyOf(elements, len + 1);
		newElements[len] = e;
		setArray(newElements);
		return true;
	} finally {
		lock.unlock();
	}
}
* */
