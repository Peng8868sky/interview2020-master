package com.study.jvmgc;

import java.util.HashMap;
import java.util.WeakHashMap;

public class WeakHashMapDemo {
    public static void main(String[] args) {
        myHashMap();
        System.out.println("=====================");
        myWeakHashMap();
    }

    public static void myHashMap() {
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        Integer key = new Integer(1);  //key指向Integer引用
        String value = "HashMap";

        map.put(key, value);
        System.out.println(map);


        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());

    }
    public static void myWeakHashMap() {
        WeakHashMap<Integer, String> map = new WeakHashMap<Integer, String>();
        Integer key = new Integer(2);
        String value = "WeakHashMap";

        map.put(key, value);
        System.out.println(map);

        key = null;
        System.out.println(map);

        System.gc();
        System.out.println(map + "\t" + map.size());
    }
    
}
