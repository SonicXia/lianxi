package com.sonic.IO;

import java.io.*;
import java.util.Date;

/**
 * @author Sonic
 */
public class ObjectTest02 {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 写出（序列化）
		ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("d://obj.ser")));
		// 操作数据类型 + 数据
		oos.writeUTF("不要加班");
		oos.writeInt(18);
		oos.writeBoolean(false);
		oos.writeChar('a');

		oos.writeObject(new Date());
		oos.writeObject(new Employee("Sonic", 18));

		oos.flush();
		oos.close();

		// 读取（反序列化）
		ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d://obj.ser")));
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
		ois.close();
	}
}



