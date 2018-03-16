package com.example.song.mvpdemo.bean;

import javax.inject.Inject;

/**
 * Description:
 * Created by song on 2018/3/12.
 * email：bjay20080613@qq.com
 */

public class StudentBean {
    private String name;
    private String no;

    @Inject
    public StudentBean() {
        this.name = "songy";
        this.no = "1";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return name+"\n"+no;
    }
}
