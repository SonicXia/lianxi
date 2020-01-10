package com.sonic.udp;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 接收端
 *
 * 1、使用 DatagramSocket指定端口，创建接收器
 * 2、准备容器，封装成 DatagramPacket包裹
 * 3、阻塞式接收包裹 receive(DatagramPacket p)
 * 4、分析数据，将字节数组还原为对应的类型
 * 5、释放资源
 *
 * @author Sonic
 */
public class UdpTypeServer {
	public static void main(String[] args) throws Exception {
		System.out.println("接收方启动中。。。");
		// 1、使用 DatagramSocket指定端口，创建接收器
		DatagramSocket server = new DatagramSocket(9999);
		// 2、准备容器，封装成 DatagramPacket包裹
		byte[] container = new byte[1024 * 60];
		DatagramPacket packet =
				new DatagramPacket(container, 0, container.length);
		// 3、阻塞式接收包裹 receive(DatagramPacket p)
		server.receive(packet); // 阻塞式
		// 4、分析数据，将字节数组还原为对应的类型
		byte[] datas = packet.getData();
		int length = packet.getLength();

		// 读取
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		// 顺序与写出一致
		String str = dis.readUTF();
		int i = dis.readInt();
		boolean b = dis.readBoolean();
		char c = dis.readChar();
		System.out.println(str);

		// 5、释放资源
		server.close();
	}

}
