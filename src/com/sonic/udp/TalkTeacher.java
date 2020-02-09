package com.sonic.udp;

/**
 * 加入多线程，实现双向交流，模拟在线咨询
 */
public class TalkTeacher {
    public static void main(String[] args) {
        new Thread(new TalkReceive(9999)).start();
        new Thread(new TalkSend(8888, "localhost", 7777)).start();
    }
}
