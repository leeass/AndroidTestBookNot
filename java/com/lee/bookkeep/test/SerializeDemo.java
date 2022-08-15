package com.lee.bookkeep.test;

import java.io.Serializable;

public class SerializeDemo implements Serializable {
    private int id;

    private String name;
    private int age;
    public static final long serialVersionUID  = 1l;

    public SerializeDemo(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String toString() {
        return "id ："+id+"name: "+name+"age:"+age ;
    }
}
