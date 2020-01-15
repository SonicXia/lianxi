package com.sonic.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 基本流程：发送端
 *
 * 1、使用 DatagramSocket指定端口，创建发送器
 * 2、准备数据，一定要转成【字节数组】
 * 3、封装成 DatagramPacket包裹，需要指定目的地
 * 4、发送包裹send(DatagramPacket p)
 * 5、释放资源
 *
 * @author Sonic
 */
public class UdpClient {
	public static void main(String[] args) throws Exception {
		System.out.println("发送方启动中。。。");
		// 1、使用 DatagramSocket指定端口，创建发送器
		DatagramSocket client = new DatagramSocket(8888);
		// 2、准备数据，一定要转成字节数组
		String data = "今天星期五";
		byte[] datas = data.getBytes();
		// 3、封装成 DatagramPacket包裹，需要指定目的地
		DatagramPacket packet =
				new DatagramPacket(datas, 0, datas.length,
						new InetSocketAddress("localhost", 9999)); // 对应 server
		// 4、发送包裹send(DatagramPacket p)
		client.send(packet);
		// 5、释放资源
		client.close();
	}

}
