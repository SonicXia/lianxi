package com.sonic.test;

import java.util.Arrays;

/**
 * @author Sonic
 */
public class Test {

	private static void removeElement(String[] s, int index) {
		System.arraycopy(s, index + 1, s, index, s.length - index -1);
		s[s.length - 1] = null;
		for (int i = 0; i < s.length; i++) {
			System.out.println(i + "--" + s[i]);
		}
	}

	private static String[] extendRange(String[] s1) {
		String[] s2 = new String[s1.length + 10];
		System.arraycopy(s1, 0, s2, 0, s1.length);
		for (int i = 0; i < s2.length; i++) {
			System.out.println(i + "--" + s2[i]);
		}
		return s2;
	}

	private static String[] addElements(String[] s1, String[] s2) {
		String[] s3 = new String[s1.length + s2.length];
		System.arraycopy(s1, 0, s3, 0, s1.length);
		System.arraycopy(s2, 0, s3, s1.length, s2.length);
		for (int i = 0; i < s3.length; i++) {
			System.out.println(i + "--" + s3[i]);
		}
		return s3;
	}



	private static int[] testBubbleSort(int[] values) {
		int temp;
		for (int i = 0; i < values.length; i++) { // i 用来确定外循环是确定第几位（倒着数）
			System.out.println("i = " + i);
			boolean isComplate = true;
			for (int j = 0; j < values.length - 1 - i; j++) { // j 用来确定内循环是哪两个数在做比较（j, j+1）
				if (values[j] > values[j + 1]) {
					temp = values[j];
					values[j] = values[j + 1];
					values[j + 1] = temp;
					isComplate = false;
				}
				System.out.println(Arrays.toString(values) + "  j = " + j);
			}
			if (isComplate) {
				break;
			}
			System.out.println("================");
		}
		return values;

	}

	private static int binarySearch(int[] values, int search) {
		int low = 0;
		int high = values.length - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (search == values[mid]) {
				return mid;
			}
			if (search < values[mid]) {
				high = mid - 1;
			}
			if (search > values[mid]) {
				low = mid + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		String[] s = {"a", "b", "c", "d"};
		String[] ss = {"e", "f", "g", "h"};
//		removeElement(s,0);
//		extendRange(s);
//		addElements(s, ss);

		int[] values = new int[]{0,1,6,4,9,7,3,5,2,8};

//		testBubbleSort(values);

		int[] values2 = new int[]{32,12,6,37,25,11,8,55,44,21,89};
		Arrays.sort(values2);
		System.out.println(Arrays.toString(values2));
		int i = binarySearch(values2, 37);
		System.out.println(i);
	}







}
