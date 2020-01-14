package com.sonic.chat04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 在线聊天室：服务器
 * 目标：加入容器实现群聊
 *
 * @author Sonic
 */
public class Chat {
	// 存放线程容器（CopyOnWriteArrayList 适用于多线程高并发环境下的读写操作）
	private static CopyOnWriteArrayList<Channel> all = new CopyOnWriteArrayList<>();

	public static void main(String[] args) throws IOException {
		System.out.println("------- Server -------");
		// 1、指定端口，使用ServerSocket创建服务器
		ServerSocket server = new ServerSocket(8888);
		// 2、阻塞式等待连接
		while (true) {
			Socket client = server.accept();
			System.out.println("一个客户端建立了链接");
			Channel c = new Channel(client);
			all.add(c); // 管理所有的成员
			new Thread(c).start();
		}
	}

	// 一个客户代表一个 Channel
	static class Channel implements Runnable {
		private Socket client;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean isRunning;
		private String name;

		public Channel(Socket client) {
			this.client = client;
			try {
				this.dis = new DataInputStream(client.getInputStream());
				this.dos = new DataOutputStream(client.getOutputStream());
				isRunning = true;
				// 获取名称
				this.name = receive();
				this.send("欢迎你的到来");
				this.sendOthers(this.name + "进入聊天室", true);
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

		// 群聊（获取自己的消息，发送给其他人）
		private void sendOthers(String msg, boolean isSys) {
			for (Channel other : all) {
				if (other == this) {
					continue;
				}
				if (isSys) {
					other.send(msg);
				} else {
					other.send(this.name + "对所有人说：" + msg);
				}
			}
		}

		// 释放资源
		private void release() {
			this.isRunning = false;
			SonicUtils.close(dos, dis, client);
			// 退出
			all.remove(this);
			sendOthers(this.name + "离开聊天室", true);
		}

		@Override
		public void run() {
			while (isRunning) {
				String msg = receive();
				if (!msg.equals("")) {
//					send(msg);
					sendOthers(msg, false);
				}
			}
		}
	}

}
