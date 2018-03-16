package com.example.song.mvpdemo.base;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Description:
 * Created by song on 2018/3/12.
 * email：bjay20080613@qq.com
 */

public class BaseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.openLog();
        ARouter.openDebug();
        ARouter.init(this);

    }
}
