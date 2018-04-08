package com.example.song.mvpdemo;

import com.example.song.mvpdemo.base.BaseCallBack;

/**
 * Description: model->presenter
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public interface MvpCallBack<T> extends BaseCallBack<String>{

    void onSuccess(String data);

    void onFailure(String msg);

    void onError();

    void onComplete();


}
