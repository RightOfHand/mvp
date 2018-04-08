package com.example.song.mvpdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.song.mvpdemo.base.BaseActivity;
import com.example.song.mvpdemo.base.Paths;
import com.example.song.mvpdemo.bean.SimpleModule;
import com.example.song.mvpdemo.bean.StudentBean;
import com.example.song.mvpdemo.inject.DaggerSecondCompent;
import com.example.song.mvpdemo.inject.SimpleFactoryActivityComponent;

import java.util.Random;

import javax.inject.Inject;

import me.kaede.tagview.OnTagClickListener;
import me.kaede.tagview.OnTagDeleteListener;
import me.kaede.tagview.Tag;
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
        DaggerSecondCompent.build().module(new SimpleModule(this))
                .build().inject(this);
        tag();

//        TextView textView=(TextView) findViewById(R.id.textView);
//        textView.setText(studentBean.toString());
//        textView.setOnClickListener(this);
//        Log.e(TAG, "onCreate: "+studentBean.toString());
    }

    void tag(){
        findViewById(R.id.tv_add).setOnClickListener(this);
        findViewById(R.id.tv_start_activity).setOnClickListener(this);
        findViewById(R.id.tv_list_activity).setOnClickListener(this);
        findViewById(R.id.tv_recyclerview_activity).setOnClickListener(this);
        editText = (EditText) findViewById(R.id.edit_tag);
        tagView = (TagView) this.findViewById(R.id.tagview);
        //SET LISTENER
        tagView.setOnTagClickListener(new OnTagClickListener() {

            @Override
            public void onTagClick(int position, Tag tag) {
                Toast.makeText(SecondActivity.this, "click tag id = " + tag.id + " position = " + position, Toast.LENGTH_SHORT).show();
            }
        });
        tagView.setOnTagDeleteListener(new OnTagDeleteListener() {

            @Override
            public void onTagDeleted(int position, Tag tag) {
                Toast.makeText(SecondActivity.this, "delete tag id = " + tag.id + " position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
        String[] tags = getResources().getStringArray(R.array.continents);
        tagView.addTags(tags);
        random = new Random();
        String[] colors = this.getResources().getStringArray(R.array.colors);
        for (int i = 1; i < colors.length; i++) {
            Tag tag = new Tag("Colorful Text");
            tag.tagTextColor = Color.parseColor(colors[i]);
            tagView.addTag(tag);
        }
        for (String item : colors) {
            Tag tag = new Tag("Colorful Background");
            tag.layoutColor = Color.parseColor(item);
            tagView.addTag(tag);
        }
        Tag tag = new Tag("Border");
        tag.layoutBorderSize = 1f;
        tagView.addTag(tag);

        tag = new Tag("Border");
        tag.layoutBorderSize = 2f;
        tag.layoutBorderColor = Color.parseColor(colors[1]);
        tagView.addTag(tag);

        tag = new Tag("Border");
        tag.layoutBorderSize = 3f;
        tag.layoutBorderColor = Color.parseColor(colors[3]);
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 0f;
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 20f;
        tagView.addTag(tag);

        tag = new Tag("Round Corner");
        tag.radius = 60f;
        tagView.addTag(tag);

        tag = new Tag("Deletable");
        tag.isDeletable = true;
        tagView.addTag(tag);
    }
    //ARouter.getInstance().build(Paths.MAIN_ACTIVITY).navigation();
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add:
                String string = "ADD A TAG";
                if (editText.getText().toString() != null && !editText.getText().toString().equals(""))
                    string = editText.getText().toString();
                Tag tag = new Tag(string);
                int r = random.nextInt(2);
                if (r == 0) tag.isDeletable = true;
                r = random.nextInt(5);
                tag.layoutColor = Color.parseColor(SecondActivity.this.getResources().getStringArray(R.array.colors)[r]);
                tagView.addTag(tag);
                break;
        }
    }
}
