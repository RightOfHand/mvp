package com.example.song.mvpdemo;

import com.example.song.mvpdemo.base.BaseCallBack;
import com.example.song.mvpdemo.base.BasePresenter;

/**
 * Description:
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public class MvpPresenter extends BasePresenter<MvpView>{

    public MvpPresenter(){

    }
//    public MvpPresenter(MvpView mvpView) {
//        this.mvpView = mvpView;
//    }

    @SuppressWarnings("deprecation")
    public void getData(String param){
        if (isViewAttached()) return;
        MvpModel.getNetData(param, new BaseCallBack<String>() {
            @Override
            public void onSuccess(String data) {

                if (isViewAttached())   getView().showSuccessData(data);
            }

            @Override
            public void onFailure(String msg) {
                if (isViewAttached()) getView().showToast(msg);
            }

            @Override
            public void onError() {
                if (isViewAttached()) getView().showErr();
            }

            @Override
            public void onComplete() {
                if (isViewAttached())  getView().hideLoading();
            }
        });
    }
}
