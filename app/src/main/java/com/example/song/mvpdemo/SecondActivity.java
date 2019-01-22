package com.example.song.mvpdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.song.mvpdemo.base.BaseActivity;
import com.example.song.mvpdemo.base.Paths;
import com.example.song.mvpdemo.bean.StudentBean;

import java.util.Random;

import javax.inject.Inject;

import me.kaede.tagview.TagView;

@Route(path = Paths.SECOND_ACTIVITY)
public class SecondActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "SecondActivity";
    @Inject
    StudentBean studentBean;
    private TagView tagView;
    private EditText editText;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        DaggerSecondCompent.build().module(new SimpleModule(this))
//                .build().inject(this);

    }

    //ARouter.getInstance().build(Paths.MAIN_ACTIVITY).navigation();
    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
