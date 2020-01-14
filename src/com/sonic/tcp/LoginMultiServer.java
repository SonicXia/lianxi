package com.sonic.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 模拟登陆（多个客户端请求）
 *
 * 创建服务器
 * 1、指定端口，使用 ServerSocket创建服务器
 * 2、阻塞式等待连接
 * 3、操作：输入输出流操作
 * 4、释放资源
 *
 * @author Sonic
 */
public class LoginMultiServer {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Server -------");
		// 1、指定端口，使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		boolean isRunning = true;
		// 2、阻塞式等待连接 accept
		while (isRunning) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了链接");
			new Thread(new Channel(client)).start();
		}
		server.close();
	}

	// 一个 channel就代表一个客户端
	static class Channel implements Runnable {
		private Socket client;
		// 输入流
		private DataInputStream dis;
		// 输出流
		private DataOutputStream dos;

		public Channel(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
				release();
			}
		}

		@Override
		public void run() {
			// 3、操作：输入输出流操作
			String uname = "";
			String upwd = "";
			// 分析
			String[] dataArray = receive().split("&");
			for (String info : dataArray) {
				String[] userInfo = info.split("=");
				if (userInfo[0].equals("uname")) {
					System.out.println("your name is " + userInfo[1]);
					uname = userInfo[1];
				} else if (userInfo[0].equals("upwd")) {
					System.out.println("your pwd is " + userInfo[1]);
					upwd = userInfo[1];
				}
			}
			// 发送数据
			if (uname.equals("Sonic") && upwd.equals("123")) {
				send("login success！");
			} else {
				send("login failed！");
			}

			// 4、释放资源
			release();

		}

		// 接收数据
		private String receive() {
			String datas ="";
			try {
				datas = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return datas;
		}

		// 发送数据
		private void send(String msg) {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 释放资源
		private void release() {
			try {
				if (dos != null) {
					dos.close();
				}
				if (dis != null) {
					dis.close();
				}
				if (client != null) {
					client.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
