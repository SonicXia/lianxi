package com.sonic.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端：使用面向对象封装
 */
public class TalkReceive implements Runnable {
    private DatagramSocket server;
    public TalkReceive(int port) {
        try {
            this.server = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 5、释放资源
        server.close();
    }
}
