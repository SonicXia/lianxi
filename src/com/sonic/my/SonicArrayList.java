package com.sonic.my;

/**
 * @author Sonic
 */
public class SonicArrayList<E> {

	private Object[] elementData;
	private int size; // 类似于当前最大位置的指针

	private static final int DEFAULT_CAPACITY = 10;

	public SonicArrayList() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	public SonicArrayList(int capacity) {
		if (capacity < 0) {
			throw new RuntimeException("容器的容量不能为负数");
		} else if (capacity == 0) {
			elementData = new Object[DEFAULT_CAPACITY];
		} else {
			elementData = new Object[capacity];
		}
	}

	public void add(E element) {
		if (size == elementData.length) {
			Object[] newArray = new Object[elementData.length + (elementData.length >> 1)];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
		elementData[size++] = element;
	}

	public E get(int index) {
		checkRange(index);
		return (E)elementData[index];
	}

	public SonicArrayList set(int index, E element) {
		checkRange(index);
		elementData[index] = element;
		return this;
	}

	public void checkRange(int index) {
		// 索引合法判断[0, size)
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("索引不合法：" + index);
		}
	}

	public SonicArrayList remove(E element) {
		for (int i = 0; i < size; i++) {
			if (element.equals(get(i))) {
				System.arraycopy(elementData, i+1, elementData, i, elementData.length - i - 1);
				elementData[--size] = null;

			}
		}
//		System.out.println("size = " + size);
//		System.out.println("length = " + elementData.length);
		return this;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0 ? true : false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size; i++) {
			sb.append(elementData[i] + ",");
		}
		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static void main(String[] args) {
		SonicArrayList<String> list = new SonicArrayList<>();

		for (int i = 0; i < 20; i++) {
			list.add("Sonic" + i);
		}

		System.out.println(list.toString());
//		System.out.println(list.get(3));
//		System.out.println(list.set(88, "AA").toString());

		System.out.println(list.remove("Sonic3"));

		System.out.println(list.size());
		System.out.println(list.isEmpty());

	}
}
