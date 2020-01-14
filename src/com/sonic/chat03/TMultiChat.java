package com.sonic.chat03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 在线聊天室：服务器
 * 目标：封装多线程实现多个客户可以正常收发多条消息
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
			new Thread(new Channel(client)).start();
		}
	}

	// 一个客户代表一个 Channel
	static class Channel implements Runnable {
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning;

		public Channel(Socket client) {
			this.client = client;
			try {
				this.dis = new DataInputStream(client.getInputStream());
				this.dos = new DataOutputStream(client.getOutputStream());
				isRunning = true;
			} catch (IOException e) {
				System.out.println("----- 服务器创建时出错啦 -----");
				release();
			}
		}

		// 接收消息
		private String receive() {
			String msg = "";
			try {
				msg = dis.readUTF();
			} catch (IOException e) {
				System.out.println("----- 服务器接收时出错啦 -----");
				release();
			}
			return msg;
		}
		// 发送消息
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				System.out.println("----- 服务器发送时出错啦 -----");
				release();
			}
		}
		// 释放资源
		private void release() {
			this.isRunning = false;
			SonicUtils.close(dos, dis, client);
		}

		@Override
		public void run() {
			while (isRunning) {
				String msg = receive();
				if (!msg.equals("")) {
					send(msg);
				}
			}
		}
	}

}
