package com.sonic.chat05;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 使用多线程封装：接收端
 * 1、接收消息
 * 2、释放资源
 * 3、重写 run()
 *
 * @author Sonic
 */
public class Receive implements Runnable{
	private DataInputStream dis;
	private Socket client;
	private boolean isRunning;

	public Receive(Socket client) {
		this.client = client;
		this.isRunning = true;
		try {
			this.dis = new DataInputStream(client.getInputStream());
//			isRunning = true;
		} catch (IOException e) {
			System.out.println("----- 客户端创建接收时出错啦 -----");
			release();
		}
	}

	// 接收消息
	private String receive() {
		String msg = "";
		try {
			msg = dis.readUTF();
		} catch (IOException e) {
			System.out.println("----- 客户端发送时出错啦 -----");
			release();
		}
		return msg;
	}

	// 释放资源
	private void release() {
		this.isRunning = false;
		SonicUtils.close(dis, client);
	}

	@Override
	public void run() {
		while (isRunning) {
			String msg = receive();
			if (!msg.equals("")) {
				System.out.println(msg);
			}
		}
	}
}
