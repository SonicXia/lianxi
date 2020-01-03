package com.sonic.IO;

import java.io.*;

/**
 * @author Sonic
 */
public class DataTest {

	public static void main(String[] args) throws IOException {
		// 写出
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(baos));
		// 操作数据类型 + 数据
		dos.writeUTF("不要加班");
		dos.writeInt(18);
		dos.writeBoolean(false);
		dos.writeChar('a');
		dos.flush();
		byte[] datas = baos.toByteArray();
		System.out.println(datas.length);

		// 读取
		DataInputStream dis = new DataInputStream(new BufferedInputStream(new ByteArrayInputStream(datas)));
		// 顺序与写出一致
		String str = dis.readUTF();
		int i = dis.readInt();
		boolean b = dis.readBoolean();
		char c = dis.readChar();
		System.out.println(str);


	}

}
