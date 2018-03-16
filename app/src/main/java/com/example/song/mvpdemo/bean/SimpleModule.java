package com.example.song.mvpdemo.bean;

import com.example.song.mvpdemo.SecondActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Description:
 * Created by song on 2018/3/12.
 * email：bjay20080613@qq.com
 */

@Module
public class SimpleModule {
    private SecondActivity secondActivity;

    public SimpleModule(SecondActivity secondActivity) {
        this.secondActivity = secondActivity;
    }
    @Provides
    StudentBean provideStudent(){return new StudentBean();}
}
