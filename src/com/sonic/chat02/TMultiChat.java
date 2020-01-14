package com.sonic.chat02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务器
 * 目标：使用多线程实现多个客户可以正常收发多条消息
 * 问题：
 * 	1、代码不好维护
 * 	2、客户端读写没有分开，必须先写后读
 *
 * @author Sonic
 */
public class TMultiChat {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Server -------");
		// 1、指定端口，使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接
		while (true) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了链接");
			new Thread(() -> {
				try {
					DataInputStream dis = new DataInputStream(client.getInputStream());
					DataOutputStream dos = new DataOutputStream(client.getOutputStream());
					boolean isRunning = true;
					while (isRunning) {
						// 3、接收消息
						String datas = dis.readUTF();
						// 4、返回消息
						dos.writeUTF(datas);
						dos.flush();
					}
					// 释放资源
					if (null != dos) {
						dos.close();
					}
					if (null != dis) {
						dis.close();
					}
					if (null != client) {
						client.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
