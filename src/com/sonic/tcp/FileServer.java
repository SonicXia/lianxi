package com.sonic.tcp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 熟悉流程
 * 创建服务器
 * 1、指定端口，使用 ServerSocket创建服务器
 * 2、阻塞式等待连接
 * 3、操作：文件拷贝
 * 4、释放资源
 *
 * @author Sonic
 */
public class FileServer {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Server -------");
		// 1、指定端口，使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接
		Socket client = server.accept();
		System.out.println("一个客户端建立了链接");
		// 3、操作：文件拷贝
		InputStream is = new BufferedInputStream(client.getInputStream());
		OutputStream os = new BufferedOutputStream(new FileOutputStream("里美tcp.png"));
		byte[] flush = new byte[1024];
		int len = -1;
		while ((len = is.read(flush)) != -1) {
			os.write(flush, 0, len);
		}
		os.flush();
		// 4、释放资源
		os.close();
		is.close();
		client.close();
		server.close();
	}

}
