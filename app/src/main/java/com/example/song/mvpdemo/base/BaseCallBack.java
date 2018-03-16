package com.example.song.mvpdemo.base;

/**
 * Description:
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public interface BaseCallBack<T> {
    void onSuccess(T data);

    void onFailure(String msg);

    void onError();

    void onComplete();
}
