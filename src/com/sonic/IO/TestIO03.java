package com.sonic.IO;

import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 * @author Sonic
 */
public class TestIO03 {

	public static void main(String[] args) {
		byte[] b = "abcdefg".getBytes();
		test(b);
	}

	/**
	 * FileInputStream是把文件当做数据源，
	 * ByteArrayInputStream则是把内存中的”某个字节数组对象”当做数据源。
	 *
	 * @param b
	 */
	public static void test(byte[] b) {
		ByteArrayInputStream bais = null;
		StringBuilder sb = new StringBuilder();
		int temp = 0;
		// 用于保存读取的字节数
		int num = 0;

		try {
			// 该构造方法的参数是一个字节数组，这个字节数组就是数据源
			bais = new ByteArrayInputStream(b);
			while ((temp = bais.read()) != -1) {
				sb.append((char)temp);
				num++;
			}
			System.out.println(sb);
			System.out.println("读取的字节数：" + num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bais != null) {
				try {
					bais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
