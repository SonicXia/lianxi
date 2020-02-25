package com.sonic.interview.oom;

public class StackOverFlowErrorDemo {
    public static void main(String[] args) {
        stackOverFlowError();
    }
    private static void stackOverFlowError() {
        stackOverFlowError();
    }
}
