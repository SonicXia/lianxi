package com.sonic.IO;

import java.io.*;

/**
 * @author Sonic
 */
public class TestFileCopy {

	public static void main(String[] args) {
//		copyFile("i:/a.txt", "h:/b.txt");
//		copyFile2("i:/a.txt", "h:/b.txt");
		copyFile3("i:/a.txt", "h:/b.txt");
	}

	/**
	 * 文件字节流 拷贝文件
	 *
	 * @param src
	 * @param dec
	 */
	static void copyFile (String src, String dec) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		// 为了提高效率，设置缓存数组！（读取的字节数据会暂时存在放在该字节数组中）
		byte[] buffer = new byte[1024];
		int temp = 0;
		try {
			fis = new FileInputStream(src);
			fos = new FileOutputStream(dec);
			// 边读边写
			// temp指的是本次读取的真是长度，temp等于-1时表示读取结束
			while ((temp = fis.read(buffer)) != -1) {
				/*
				将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
				如果是用 fos.write(buffer)方法，那么写入的长度将会是1024，即缓存数组定义的长度
				 */
				fos.write(buffer, 0, temp);
			}
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
			// 两个流需要分别关闭（先开的后关，后开的先关）
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 文件字符流 拷贝文件
	 * 字节流不能很好的处理Unicode字符，经常会出现“乱码”现象。
	 * 所以，我们处理文本文件，一般可以使用文件字符流，它以字符为单位进行操作
	 *
	 * @param src
	 * @param dec
	 */
	static void copyFile2 (String src, String dec) {
		// 写法和使用Stream基本一致，只不过，读取时是按照字符读取
		FileReader fr = null;
		FileWriter fw = null;
		// 为了提高效率，设置缓存字符数组！
		char[] buffer = new char[1024];
		int temp = 0;
		try {
			fr = new FileReader(src);
			fw = new FileWriter(dec);
			// 边读边写
			// temp指的是本次读取的真是长度，temp等于-1时表示读取结束
			while ((temp = fr.read(buffer)) != -1) {
				/*
				将缓存数组中的数据写入文件中，注意：写入的是读取的真实长度；
				如果是用 fos.write(buffer)方法，那么写入的长度将会是1024，即缓存数组定义的长度
				 */
				fw.write(buffer, 0, temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			// 两个流需要分别关闭（先开的后关，后开的先关）
			if (fr != null) {
				try {
					fr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 缓冲文件字符流 拷贝文件
	 *
	 * @param srcPath
	 * @param destPath
	 */
	static void copyFile3 (String srcPath, String destPath) {
		final File src = new File(srcPath);
		final File dest = new File(destPath);

		// 使用try（）,在括号中定义流，try,catch或者finally结束的时候，会自动关闭。jdk7开始支持
		try (final BufferedReader br = new BufferedReader(new FileReader(src));
			 final BufferedWriter bw = new BufferedWriter(new FileWriter(dest))) {
			String line = null;
			while ((line = br.readLine()) != null) {
				bw.write(line);
				bw.newLine();
			}
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
