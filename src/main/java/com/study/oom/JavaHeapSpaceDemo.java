package com.study.oom;

import java.util.Random;

public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "weiwozongheng";
        while (true) {
            // Exception in thread "main" java.lang.OutOfMemoryError: Java heap space
            str += str + new Random().nextInt(11111111) + new Random().nextInt(222222);
            str.intern();
        }
    }
}
