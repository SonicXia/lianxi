package com.sonic.udp;

import com.sonic.IO.Employee;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

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
public class UdpObjServer {
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

		// 读取（反序列化）
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		// 顺序与写出一致
		String str = ois.readUTF();
		int i = ois.readInt();
		boolean b = ois.readBoolean();
		char c = ois.readChar();
		// 对象的数据还原
		Object d = ois.readObject();
		Object e = ois.readObject();

		if (d instanceof Date) {
			Date date = (Date) d;
			System.out.println(date);
		}
		if (e instanceof Employee){
			Employee emp = (Employee) e;
			System.out.println(emp.toString());
		}

		// 5、释放资源
		server.close();
	}

}
