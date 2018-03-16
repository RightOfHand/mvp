package com.example.song.mvpdemo.base;

import android.content.Context;

/**
 * Description:
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public interface BaseView {
    /**
     * 显示正在加载view
     */
    void showLoading();
    /**
     * 关闭正在加载view
     */
    void hideLoading();
    /**
     * 显示提示
     * @param msg
     */
    void showToast(String msg);
    /**
     * 显示请求错误提示
     */
    void showErr();
    /**
     * 获取上下文
     * @return 上下文
     */
    Context getContext();

}
