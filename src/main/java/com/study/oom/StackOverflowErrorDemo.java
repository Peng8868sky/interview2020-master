package com.study.oom;

public class StackOverflowErrorDemo {
    public static void main(String[] args) {
        StackOverflowError();
    }

    private static void StackOverflowError() {
        // Exceprion in thread "main" java.lang.StackOverflowError
        StackOverflowError();
    }
}
