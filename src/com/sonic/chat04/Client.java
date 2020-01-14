package com.sonic.chat04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：封装多线程实现多个客户可以正常收发多条消息
 *
 * @author Sonic
 */
public class Client {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Client -------");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("请输入用户名：");
		String name = br.readLine();
		// 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、客户端发送消息
		new Thread(new Send(client, name)).start();
		// 3、接收消息
		new Thread(new Receive(client)).start();
	}
}
