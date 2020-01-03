package com.sonic.my;

/**
 * @author Sonic
 */
public class SonicLinkedList<E> {

	private Node first;	// 首Node
	private Node last;  // 尾Node

	private int size;

	/**
	 * 默认追加元素
	 *
	 * @param e
	 */
	public void add(E e) {
		Node node = new Node(e);
		if (first == null) {
			first = node;
			last = node;
		} else {
			node.previous = last;
			node.next = null;

			last.next = node;
			last = node;
		}
		size++;
	}

	/**
	 * 在指定位置插入元素
	 *
	 * @param index
	 * @param e
	 */
	public void add(int index, E e) {
		checkRange(index);

		Node newNode = new Node(e);
		Node temp = getNode(index);

		if (first == null) {
			first = newNode;
			last = newNode;
		} else {
			if(temp != null) {
				Node up = temp.previous;
				if(index == 0) {
					first = newNode;
					newNode.previous = null;
					newNode.next = temp;
					temp.previous = newNode;
				} else if(index == size) {
					last = newNode;
					up = temp.previous;
					newNode.previous = up;
					newNode.next = null;
					up.next = newNode;
				} else if (index > 0 && index < size) {
					newNode.previous = up;
					newNode.next = temp;
					up.next = newNode;
					temp.previous = newNode;
				}
			}
		}
		size++;
	}

	/**
	 * 删除指定位置元素
	 *
	 * @param index
	 * @return
	 */
	public SonicLinkedList remove(int index) {
		checkRange(index);
		Node temp = getNode(index);
		if(temp != null) {
			Node up = temp.previous;
			Node down = temp.next;

			if(up != null) {
				up.next = down;
			}
			if(down != null) {
				down.previous = up;
			}
			// 被删除的是第一个元素
			if(index == 0) {
				first = down;
			}
			// 被删除的是最后一个元素
			if(index == size - 1) {
				last = up;
			}

			size--;
		}
		return this;
	}

	/**
	 * 获取指定位置元素
	 *
	 * @param index
	 * @return
	 */
	public E get(int index) {
		checkRange(index);
		Node temp = getNode(index);
		return temp != null ? (E)temp.element : null;
	}

	private Node getNode(int index) {
		Node temp = null;
		// 查找的位置在链表的前半段
		if(index <= (size >> 1)) { // size>>1 相当于除以2
			temp = first; // 从首Node开始往后找
			for (int i = 0; i < index; i++) {
				temp = temp.next;
			}
		}
		// 查找的位置在链表的后半段
		else {
			temp = last; // 从尾Node开始往前找
			for (int i = size - 1; i > index; i--) {
				temp = temp.previous;
			}
		}
		return temp;
	}

	private void checkRange(int index) {
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("索引数字不合法：" + index);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		Node temp = first;
		while (temp != null) {
			sb.append(temp.element + ",");
			temp = temp.next;
		}

		sb.setCharAt(sb.length() - 1, ']');
		return sb.toString();
	}

	public static void main(String[] args) {
		SonicLinkedList<String> list = new SonicLinkedList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.add("H");
		list.add("I");
		list.add("J");

		System.out.println(list.toString());
		System.out.println(list.size);
//		System.out.println(list.get(0));
//		System.out.println(list.remove(0));
		list.add(2, "哈哈");
		System.out.println(list.toString());
		System.out.println(list.size);

	}

}
