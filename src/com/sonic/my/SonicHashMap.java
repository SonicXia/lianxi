package com.sonic.my;

/**
 * @author Sonic
 */
public class SonicHashMap<K, V> {

	Node3[] table; // 位桶数组
	int size;      // 存放的键值对个数

	public SonicHashMap() {
		table = new Node3[16]; // 长度一般定义为2的整数次幂
	}

	/**
	 * 添加对象
	 *
	 * @param key
	 * @param value
	 */
	public void put(K key, V value) {

		// TODO: 2019/12/13  考虑数组自动扩容：初始16，每次扩容后的大小为上一次容量的2倍，即16*2*2*2...
		
		Node3 newNode = new Node3();
		newNode.hash = myHash(key.hashCode(), table.length);
		newNode.key = key;
		newNode.value = value;
		newNode.next = null;

		Node3 temp = table[newNode.hash]; // 取出当前 hash值对应位置的对象

		Node3 iterLast = null; // 正在遍历的最后一个元素
		boolean keyRepeat = false;

		if(temp == null) { // 如果当前取出的对象为空
			// 此处数组元素为空，则直接将新节点放入
			table[newNode.hash] = newNode;
			size++;
		} else {
			// 此处数组元素不为空，则遍历对应链表
			while(temp != null) {
				// 判断 key如果重复，则覆盖
				if(temp.key.equals(key)) {
					keyRepeat = true;
					temp.value = value; // 只是覆盖 value即可，其他的值（hash,key,next）保持不变
					break;
				} else {
					// key不重复，则遍历下一个
					iterLast = temp;
					temp = temp.next;
				}
			}
			if(!keyRepeat) { // 如果未发生 key重复的情况，则添加到链表最后
				iterLast.next = newNode;
				size++;
			}
		}
	}

	public V get(K key) {
		int hash = myHash(key.hashCode(), table.length);
		V value = null;

		if (table[hash] != null) {
			Node3 temp = table[hash];
			while (temp != null) {
				if (temp.key.equals(key)) {
					value = (V)temp.value;
					break;
				} else {
					temp = temp.next;
				}
			}
		}

		return value;
	}

	public int myHash(int v, int length) {
		System.out.println("hash in myHash:" + (v & (length - 1))); // 直接位运算，效率高
		System.out.println("hash in myHash:" + (v % (length - 1))); // 取模运算，效率低
		return v & (length - 1);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		// 遍历bucket数组
		for(int i = 0; i < table.length; i++) {
			Node3 temp = table[i];
			// 遍历链表
			while (temp != null) {
				sb.append(temp.key + ":" + temp.value + ",");
				temp = temp.next;
			}
		}
		sb.setCharAt(sb.length() - 1, '}');
		return sb.toString();
	}

	public static void main(String[] args) {
		SonicHashMap map = new SonicHashMap();
		map.put(10, "aa");
		map.put(20, "bb");
		map.put(30, "cc");
		map.put(20, "sss");

		map.put(53, "gg");
		map.put(69, "hh");
		map.put(85, "jj");

		System.out.println(map.toString());
		System.out.println(map.get(85));

	}

}
