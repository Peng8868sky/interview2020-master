package com.study.jvmgc;

public class StrongReferenceDemo {
    public static void main(String[] args) {

        Object object1=new Object();  //这样定义的就是强引用
        Object object2=object1;  //object2引用赋值
        object1=null;  //置空
        System.gc();
        System.out.println(object2);

    }
}
