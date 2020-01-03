package com.sonic.test;

import java.io.File;
import java.io.IOException;

/**
 * @author Sonic
 */
public class TestFile {

	public static void main(String[] args) throws IOException {
		File f1 = new File("C:\\Users\\Sonic\\Desktop\\聚拍需求原型V1.0-1204");
		printFile(f1, 0);
//		File f2 = new File("b.txt");
//		File f3 = new File("h:/1/2");
//		String name = f1.getName();
//		f1.createNewFile();
//		f2.createNewFile();
////		f3.mkdirs();
//		if (f3.exists()) {
//			f3.deleteOnExit();
////			if (delete) {
////				System.out.println("删除成功");
////			} else {
////				System.out.println("删除失败");
////			}
//		}



	}

	private static void printFile(File file, Integer level) {
		for (int i = 0; i < level; i++) {
			System.out.print("-");
		}

		System.out.println(file.getName());
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File temp : files) {
				printFile(temp, level + 1);
			}
		}


	}



}
