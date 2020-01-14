package com.sonic.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建客户端
 * 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
 * 2、操作：文件拷贝
 * 3、释放资源
 *
 * @author Sonic
 */
public class FileClient {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Client -------");
		// 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、操作：文件拷贝
		InputStream is = new BufferedInputStream(new FileInputStream("石原里美.jpg"));
		OutputStream os = new BufferedOutputStream(client.getOutputStream());
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		// 3、释放资源
		os.close();
		is.close();
		client.close();
	}

}
