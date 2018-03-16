package com.example.song.mvpdemo.inject;

import com.example.song.mvpdemo.SecondActivity;
import com.example.song.mvpdemo.bean.SimpleModule;

import dagger.Component;

/**
 * Description:
 * Created by song on 2018/3/12.
 * email：bjay20080613@qq.com
 */

@Component(modules = SimpleModule.class)
public interface SimpleFactoryActivityComponent {
    void inject(SecondActivity secondActivity);
}
