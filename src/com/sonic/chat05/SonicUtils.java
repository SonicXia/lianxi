package com.sonic.chat05;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author Sonic
 */
public class SonicUtils {
	/**
	 * 释放资源
	 *
	 * @param targets
	 */
	public static void close(Closeable... targets) {
		for (Closeable target : targets) {
			if (null != target) {
				try {
					target.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
