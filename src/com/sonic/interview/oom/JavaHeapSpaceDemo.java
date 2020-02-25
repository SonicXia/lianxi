package com.sonic.interview.oom;

import java.util.Random;

public class JavaHeapSpaceDemo {
    public static void main(String[] args) {
        String str = "Konna";
        while(true) {
            str += str + new Random().nextInt(111111) + new Random().nextInt(222222);
            str.intern();
        }
    }
}
