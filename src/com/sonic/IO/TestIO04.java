package com.sonic.IO;

import java.io.*;
import java.nio.Buffer;

/**
 * 所有的流，无论是输入流还是输出流，使用完毕之后，都应该关闭。 如果不关闭，会产生对资源占用的浪费。 当量比较大的时候，会影响到业务的正常开展。
 *
 * 一、在try中关闭，不推荐使用，因为当文件不存在时，try中的代码不会被执行
 *
 * 二、在finally中关闭
 *
 *   这是标准的关闭流的方式
 *     1. 首先把流的引用声明在try的外面，如果声明在try里面，其作用域无法抵达finally.
 *     2. 在finally关闭之前，要先判断该引用是否为空
 *     3. 关闭的时候，需要再一次进行try catch处理
 *
 * 三、使用try（）,在括号中定义流，try,catch或者finally结束的时候，会自动关闭。jdk7开始支持。
 *
 * @author Sonic
 */
public class TestIO04 {

	public static void main(String[] args) {
		long t1 = System.currentTimeMillis();
		copyFile2("H:\\【咕泡VIP课堂】JAVA大型互联网架构师 第2期VIP\\【咕泡独家】spring-framework-5.0.2.RELEASE-中文注释版.zip",
				"i:/haha.zip");
		long t2 = System.currentTimeMillis();
		System.out.println(t2 - t1);

	}

	public static void copyFile1(String src, String dec) {

		FileInputStream fis = null;
		FileOutputStream fos = null;

		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dec);
			// 3、操作（分段读取）
			byte[] flush = new byte[1024]; // 缓冲容器
			int len = -1; // 接收长度
			while ((len = fis.read(flush)) != -1) {
				fos.write(flush, 0 , len); // 分段写出
			}
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4、释放资源，分别关闭（先开后关，后开先关）
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void copyFile2(String srcPath, String destPath) {
		// 1、创建源
		File src = new File(srcPath);
		File dest = new File(destPath);
		// 2、选择流
		// 使用try（）,在括号中定义流，try,catch或者finally结束的时候，会自动关闭。jdk7开始支持
		try (InputStream is = new BufferedInputStream(new FileInputStream(src));
			 OutputStream os = new BufferedOutputStream((new FileOutputStream(dest)))) {
			// 3、操作（分段读取）
			byte[] flush = new byte[1024]; // 缓冲容器
			int len = -1; // 接收长度
			while ((len = is.read(flush)) != -1) {
				os.write(flush, 0 , len); // 分段写出
			}
			os.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
