package com.example.song.mvpdemo;


import android.os.Handler;

import com.example.song.mvpdemo.base.BaseCallBack;

/**
 * Description: model
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */
public class MvpModel {

    /**
     * Get net data.
     *
     * @param param       the param
     * @param mvpCallBack the mvp call back
     */
    public static void getNetData(final String param,final BaseCallBack<String> mvpCallBack){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                switch (param){
                    case "normal":
                        mvpCallBack.onSuccess("根据参数："+param+"请求success");
                        break;
                    case "failure":
                        mvpCallBack.onFailure("请求失败");
                        break;
                    case "error":
                        mvpCallBack.onError();
                        break;
                }
                mvpCallBack.onComplete();
            }
        },2000);

    }
}
