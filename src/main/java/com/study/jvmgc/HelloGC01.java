package com.study.jvmgc;

public class HelloGC01 {
    public static void main(String[] args) throws Exception{

		long totalMemory = Runtime.getRuntime().totalMemory();  // 返回java虚拟机中的内存总量
		long maxMemory = Runtime.getRuntime().maxMemory();	   // 返回java虚拟机试图使用的最大内存量

		System.out.println("TotalMemory（-Xms） = " + totalMemory + " （字节）、" + (totalMemory / (double) 1024 / 1024) + " MB");

		System.out.println("MaxMemory（-Xmx） = " + maxMemory + " （字节）、" + (maxMemory / (double) 1024 / 1024) + " MB");


    }
}
