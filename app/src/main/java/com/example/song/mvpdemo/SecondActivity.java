package com.example.song.mvpdemo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.song.mvpdemo.base.BaseActivity;
import com.example.song.mvpdemo.base.Paths;
import com.example.song.mvpdemo.bean.SimpleModule;
import com.example.song.mvpdemo.bean.StudentBean;
import com.example.song.mvpdemo.inject.DaggerSecondCompent;
import com.example.song.mvpdemo.inject.SimpleFactoryActivityComponent;

import javax.inject.Inject;

@Route(path = Paths.SECOND_ACTIVITY)
public class SecondActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "SecondActivity";
    @Inject
    StudentBean studentBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DaggerSecondCompent.build().module(new SimpleModule(this))
                .build().inject(this);

        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText(studentBean.toString());
        textView.setOnClickListener(this);
        Log.e(TAG, "onCreate: "+studentBean.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textView:
                ARouter.getInstance().build(Paths.MAIN_ACTIVITY).navigation();
                break;

        }
    }
}
