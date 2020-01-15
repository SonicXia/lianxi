package com.sonic.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 多次交流：发送端
 *
 * 1、使用 DatagramSocket指定端口，创建发送器
 * 2、准备数据，一定要转成【字节数组】
 * 3、封装成 DatagramPacket包裹，需要指定目的地
 * 4、发送包裹send(DatagramPacket p)
 * 5、释放资源
 *
 * @author Sonic
 */
public class UdpTalkClient {
	public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中。。。");
		// 1、使用 DatagramSocket指定端口，创建发送器
		DatagramSocket client = new DatagramSocket(8888);

		while (true) {
			// 2、准备数据，一定要转成字节数组（从控制台获取）
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String data = reader.readLine();
			byte[] datas = data.getBytes();
			// 3、封装成 DatagramPacket包裹，需要指定目的地
			DatagramPacket packet =
					new DatagramPacket(datas, 0, datas.length,
							new InetSocketAddress("localhost", 9999)); // 对应 server
			// 4、发送包裹send(DatagramPacket p)
			client.send(packet);

			if (data.equals("bye")) {
				break;
			}
		}

		// 5、释放资源
		client.close();
	}

}
