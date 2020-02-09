package com.sonic.udp;

/**
 * 加入多线程，实现双向交流，模拟在线咨询
 */
public class TalkStudent {
    public static void main(String[] args) {
        new Thread(new TalkSend(6666, "localhost", 9999)).start();
        new Thread(new TalkReceive(7777)).start();
    }
}
