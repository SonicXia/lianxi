package com.sonic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 多次交流：接收端
 *
 * 1、使用 DatagramSocket指定端口，创建接收器
 * 2、准备容器，封装成 DatagramPacket包裹
 * 3、阻塞式接收包裹 receive(DatagramPacket p)
 * 4、分析数据
 * 5、释放资源
 *
 * @author Sonic
 */
public class UdpTalkServer {

	public static void main(String[] args) throws Exception {
		System.out.println("接收方启动中。。。");
		// 1、使用 DatagramSocket指定端口，创建接收器
		DatagramSocket server = new DatagramSocket(9999);

		while (true) {
			// 2、准备容器，封装成 DatagramPacket包裹
			byte[] container = new byte[1024 * 60];
			DatagramPacket packet =
					new DatagramPacket(container, 0, container.length);
			// 3、阻塞式接收包裹 receive(DatagramPacket p)
			server.receive(packet); // 阻塞式
			// 4、分析数据
			byte[] datas = packet.getData();
			int length = packet.getLength();
			String data = new String(datas, 0, length);
			System.out.println(data);

			if (data.equals("bye")) {
				break;
			}
		}

		// 5、释放资源
		server.close();
	}

}
