package com.example.song.mvpdemo.bean;

/**
 * Description:
 *
 * @author by song on 2019/1/22.
 * email：bjay20080613@qq.com
 */
public class User {
    private String name;
    private  int age;

    public User( ) {
    }

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

