package com.sonic.IO;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author Sonic
 */
public class TestIO02 {

	public static void main(String[] args) {
		FileOutputStream fos = null;
		String str = "talk is cheep show me the code";
		try {
			// true表示内容会追加到文件末尾；false表示重写整个文件内容。
			fos = new FileOutputStream("i:/a.txt", true);
			// 该方法是直接将一个字节数组写入文件中; 而write(int n)是写入一个字节
			fos.write(str.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
