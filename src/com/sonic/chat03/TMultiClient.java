package com.sonic.chat03;

import java.io.*;
import java.net.Socket;

/**
 * 在线聊天室：客户端
 * 目标：封装多线程实现多个客户可以正常收发多条消息
 *
 * @author Sonic
 */
public class TMultiClient {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Client -------");
		// 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、客户端发送消息
		new Thread(new Send(client)).start();
		// 3、接收消息
		new Thread(new Receive(client)).start();
	}
}
