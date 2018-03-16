package com.example.song.mvpdemo;

import android.app.ProgressDialog;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.song.mvpdemo.base.BaseActivity;
import com.example.song.mvpdemo.base.Paths;
import com.example.song.mvpdemo.bean.StudentBean;
import com.meituan.android.walle.WalleChannelReader;

import javax.inject.Inject;

@Route(path = Paths.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity implements MvpView ,View.OnClickListener{
    private static final String TAG = "MainActivity";
    private TextView tvTitle;
    private Button btnSuccess;
    private Button btnFailure;
    private Button btnError;
    ProgressDialog progressDialog;
    MvpPresenter presenter;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvTitle=(TextView) findViewById(R.id.tvTitle);
        btnSuccess=(Button) findViewById(R.id.btnSuccess);
        btnFailure=(Button) findViewById(R.id.btnFailure);
        btnError=(Button) findViewById(R.id.btnError);

        // 初始化进度条
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");

        //初始化Presenter
        presenter = new MvpPresenter();
        presenter.attachView(this);

        btnSuccess.setOnClickListener(this);
        btnFailure.setOnClickListener(this);
        btnError.setOnClickListener(this);


        String chanel= WalleChannelReader.getChannel(this.getApplicationContext());
        readMetaDataFromApplication();
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

        }
    }
}
