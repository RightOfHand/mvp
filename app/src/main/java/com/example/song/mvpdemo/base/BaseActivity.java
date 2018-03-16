package com.example.song.mvpdemo.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


/**
 * Description:
 * Created by song on 2018/3/5.
 * email：bjay20080613@qq.com
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView{
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);

    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()) progressDialog.show();
    }

    @Override
    public void hideLoading() {
       if (progressDialog.isShowing()) progressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErr() {

    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }
}
