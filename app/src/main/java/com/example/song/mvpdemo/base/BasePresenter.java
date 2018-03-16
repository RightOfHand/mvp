package com.example.song.mvpdemo.base;

import com.example.song.mvpdemo.MvpView;

/**
 * Description:
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public class BasePresenter<V extends BaseView> {

    private V  mvpView;
    public BasePresenter(){

    }
//    public MvpPresenter(MvpView mvpView) {
//        this.mvpView = mvpView;
//    }

    public void attachView(V mvpView){
        this.mvpView=mvpView;
    }

    public void detachView(){
        this.mvpView=null;
    }

    public boolean isViewAttached(){
        return mvpView!=null;
    }

    public V getView(){
        return mvpView;
    }
}
