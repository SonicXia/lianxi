package com.sonic.tcp;

import java.io.*;
import java.net.Socket;

/**
 * 模拟登陆（多个客户端请求）
 *
 * 创建客户端
 * 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
 * 2、操作：输入输出流操作
 * 3、释放资源
 *
 * @author Sonic
 */
public class LoginMultiClient {
	public static void main(String[] args) throws IOException {
		System.out.println("------- Client -------");
		// 1、建立连接，使用 ServerSocket创建客户端 + 服务的地址和端口
		Socket client = new Socket("localhost", 8888);
		// 2、操作：输入输出流操作，先请求后响应
		new Send(client).send();
		new Receive(client).receive();
		// 3、释放资源
		client.close();
//		new Release().release();

	}

	// 接收数据
	static class Receive {
		private Socket client;
		private DataInputStream dis;

		public Receive(Socket client) {
			this.client = client;
			try {
				dis = new DataInputStream(client.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		public void receive() {
			String result = null;
			try {
				result = dis.readUTF();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(result);
		}
	}

	// 发送数据
	static class Send {
		private Socket client;
		private DataOutputStream dos;
		private BufferedReader console;
		private String msg;

		public Send(Socket client) {
			this.client = client;
			console = new BufferedReader(new InputStreamReader(System.in));
			this.msg = init();
			try {
				dos = new DataOutputStream(client.getOutputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		private String init() {
			try {
				System.out.println("请输入用户名：");
				String uname = console.readLine();
				System.out.println("请输入密码：");
				String upwd = console.readLine();
				return "uname=" + uname + "&" + "upwd=" + upwd;
			} catch (IOException e) {
				e.printStackTrace();
			}
			return "";
		}

		public void send() {
			try {
				dos.writeUTF(msg);
				dos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 释放资源
	static class Release {
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;

		public void release(Socket client, DataInputStream dis, DataOutputStream dos) {
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
