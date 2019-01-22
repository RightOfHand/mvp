package com.example.song.mvpdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.song.mvpdemo.activity.CustomDataBindActivity;
import com.example.song.mvpdemo.base.BaseActivity;

public class MainActivity extends BaseActivity implements MvpView ,View.OnClickListener{
    private static final String TAG = "MainActivity";
    private TextView tvTitle;
    private Button btnSuccess;
    private Button btnFailure;
    private Button btnError;
    ProgressDialog progressDialog;
    MvpPresenter presenter;
    private ImageView ivStaticImage,ivGifImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findView();

//

        //初始化Presenter
        presenter = new MvpPresenter();
        presenter.attachView(this);

        btnSuccess.setOnClickListener(this);
        btnFailure.setOnClickListener(this);
        btnError.setOnClickListener(this);

        ivStaticImage.setOnClickListener(this);



    }

    private void findView(){
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        btnSuccess=(Button) findViewById(R.id.btnSuccess);
        btnFailure=(Button) findViewById(R.id.btnFailure);
        btnError=(Button) findViewById(R.id.btnError);

        ivStaticImage=(ImageView) findViewById(R.id.iv_static_image);
        ivGifImage=(ImageView) findViewById(R.id.iv_git_image);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public  void getSuccessData(View view){
        presenter.getData("normal");
    }

    public  void getFailureData(View view){
        presenter.getData("failure");
    }

    public  void getErrorData(View view){
        presenter.getData("error");
    }

    @Override
    public void showLoading() {
        if (!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
     if (progressDialog.isShowing()){
         progressDialog.dismiss();
     }
    }
    private void readMetaDataFromApplication() {
        try {
            ApplicationInfo appInfo = this.getPackageManager()
                    .getApplicationInfo(getPackageName(),
                            PackageManager.GET_META_DATA);
            String mTag = appInfo.metaData.getString("mTag");

            Log.e(TAG, "mTag=" + mTag);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showSuccessData(String data) {
       tvTitle.setText(data);
    }

    void goDataBindActivity(){
        Intent intent=new Intent(MainActivity.this, CustomDataBindActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSuccess:
                getSuccessData(v);
                break;
            case R.id.btnFailure:
                getFailureData(v);
                break;
            case R.id.btnError:
                getErrorData(v);
                break;
            case R.id.iv_static_image:
                goDataBindActivity();
                break;

        }
    }
}
