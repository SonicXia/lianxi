package com.sonic.chat01;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：实现一个客户可以正常收发多条消息
 *
 * @author Sonic
 */
public class MultiClient {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Client -------");
		// 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、客户端发送消息
		DataOutputStream dos = new DataOutputStream(client.getOutputStream());
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
		DataInputStream dis = new DataInputStream(client.getInputStream());
		boolean isRunning = true;
		while (isRunning) {
			String msg = console.readLine();
			dos.writeUTF(msg);
			dos.flush();
			// 3、接收消息
			msg = dis.readUTF();
			System.out.println(msg);
		}
		// 释放资源
		dis.close();
		dos.close();
		client.close();
	}
}
