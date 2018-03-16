package com.example.song.mvpdemo.base;

import com.example.song.mvpdemo.inject.Description;

/**
 * Description:
 * Created by song on 2018/3/7.
 * email：bjay20080613@qq.com
 */

@Description("I am class annotation")
public class Child implements People {
    @Override
    @Description("i am method annotation")
    public String name() {
        return null;
    }

    @Override
    public int age() {
        return 0;
    }

    @Override
    public void work() {

    }
}
