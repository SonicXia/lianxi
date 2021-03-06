package com.sonic.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆（单向）
 *
 * 创建服务器
 * 1、指定端口，使用 ServerSocket创建服务器
 * 2、阻塞式等待连接
 * 3、操作：输入输出流操作
 * 4、释放资源
 *
 * @author Sonic
 */
public class LoginServer {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Server -------");
		// 1、指定端口，使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接
		Socket client = server.accept();
		System.out.println("一个客户端建立了链接");
		// 3、操作：输入输出流操作
		DataInputStream dis = new DataInputStream(client.getInputStream());
		String datas = dis.readUTF();
		String[] dataArray = datas.split("&");
		for (String info : dataArray) {
			String[] userInfo = info.split("=");
			if (userInfo[0].equals("uname")) {
				System.out.println("your name is " + userInfo[1]);
			} else if (userInfo[0].equals("upwd")) {
				System.out.println("your pwd is " + userInfo[1]);
			}
		}
		// 4、释放资源
		dis.close();
		client.close();

		server.close();
	}

}
