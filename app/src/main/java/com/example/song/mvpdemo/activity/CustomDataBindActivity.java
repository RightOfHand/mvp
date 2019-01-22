package com.example.song.mvpdemo.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.song.mvpdemo.R;
import com.example.song.mvpdemo.base.BaseActivity;
import com.example.song.mvpdemo.bean.User;
import com.example.song.mvpdemo.databinding.ActivityDataBinding;

/**
 * Description:
 *
 * @author by song on 2019/1/22.
 * email：bjay20080613@qq.com
 */
public class CustomDataBindActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDataBinding binding= DataBindingUtil.setContentView(this, R.layout.activity_data);
        User user=new User("songy DataBinding",27);
        binding.setUser(user);
    }
}
