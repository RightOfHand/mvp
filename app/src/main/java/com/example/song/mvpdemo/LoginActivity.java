package com.example.song.mvpdemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.allen.library.RxHttpUtils;
import com.allen.library.interceptor.Transformer;
import com.allen.library.utils.AppUtils;
import com.allen.library.utils.MD5;
import com.allen.library.utils.SPUtils;
import com.allen.library.utils.ToastUtils;
import com.tpson.kulugas.MyApplication;
import com.tpson.kulugas.activity.home.BuilderHomeActivity;
import com.tpson.kulugas.activity.home.MainActivity;
import com.tpson.kulugas.R;
import com.tpson.kulugas.activity.home.PersonHomeActivity;
import com.tpson.kulugas.activity.home.ServiceCompanyActivity;
import com.tpson.kulugas.base.BaseActivity;
import com.tpson.kulugas.base.BaseData;
import com.tpson.kulugas.base.MyDataObserver;
import com.tpson.kulugas.config.Config;
import com.tpson.kulugas.http.ApiService;
import com.tpson.kulugas.model.LoginModel;
import com.tpson.kulugas.utils.StringUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;


/**
 * @author chencunxin on 2018/6/11.
 * @Email: voynich_chen@163.com
 * --------<登录界面>---------
 */

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.et_user_name)
    EditText etUserName;
    @Bind(R.id.et_pass_word)
    EditText etPassWord;
    @Bind(R.id.cb_remember_user)
    CheckBox cbRememberUser;
    @Bind(R.id.ll_login)
    LinearLayout llLogin;
    @Bind(R.id.ll_register)
    LinearLayout llRegister;
    @Bind(R.id.ll_call_waiter)
    LinearLayout llCallWaiter;

    private ProgressDialog loading_dialog;
    public MyApplication app = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        app = (MyApplication) getApplication();
        ButterKnife.bind(this);
        initView();
        initRequestPermissions();
    }

    private void initView() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        loading_dialog = BaseActivity.getProgressDialog(this, "", "正在登陆...");
        if (SPUtils.get(Config.IS_REMEMBER, false)) {
            etUserName.setText(SPUtils.get(Config.USER_NAME, ""));
            etUserName.setSelection(SPUtils.get(Config.USER_NAME, "").length());
            etPassWord.setText(SPUtils.get(Config.PASSWORD, ""));
        }

    }

    private void login() {
        if (StringUtil.isEmpty(etUserName.getText().toString())){
            ToastUtils.showToast("请输入用户名！");
            return;
        }else if (StringUtil.isEmpty(etPassWord.getText().toString())){
            ToastUtils.showToast("请输入密码！");
            return;
        }
        RxHttpUtils.createApi(ApiService.class)
                .doLogin(etUserName.getText().toString(), MD5.EncoderByMd5(etPassWord.getText().toString()))
                .compose(Transformer.<BaseData<LoginModel>>switchSchedulers(loading_dialog))
                .subscribe(new MyDataObserver<LoginModel>(loading_dialog) {
                    @Override
                    protected void onSuccess(LoginModel data) {
                        SPUtils.put(Config.IS_REMEMBER, cbRememberUser.isChecked());
                        if (cbRememberUser.isChecked()) {
                            SPUtils.put(Config.USER_NAME, etUserName.getText().toString());
                            SPUtils.put(Config.PASSWORD, etPassWord.getText().toString());
                        } else {
                            SPUtils.remove(Config.USER_NAME);
                            SPUtils.remove(Config.PASSWORD);
                        }
                        SPUtils.put(Config.SID, data.getSid());
                        SPUtils.put(Config.USER_TYPE, data.getType());
                        switch (data.getType()) {
                            case 1:
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                break;
                            case 2:
                                startActivity(new Intent(LoginActivity.this, ServiceCompanyActivity.class));
                                break;
                            case 3:
                                startActivity(new Intent(LoginActivity.this, BuilderHomeActivity.class));
                                break;
                            case 4:
                                startActivity(new Intent(LoginActivity.this, PersonHomeActivity.class));
                                break;
                            default:
                                break;
                        }
                        finish();
                    }

                    @Override
                    protected void onError(String errorMsg) {
                        ToastUtils.showToast(errorMsg);
                    }
                });


    }

    @OnClick({R.id.cb_remember_user, R.id.ll_login, R.id.ll_register, R.id.ll_call_waiter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cb_remember_user:

                break;
            case R.id.ll_login:
                login();
                break;
            case R.id.ll_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.ll_call_waiter:
                com.tpson.kulugas.utils.AppUtils.callPhone(this, "17682311011");
                break;
        }
    }
    private long firstBackTime = 0;

    @Override
    public void onBackPressed() {
        long secondBackTime = System.currentTimeMillis();
        if (secondBackTime - firstBackTime > 3000) {
            Toast.makeText(this, "再按一次即可退出App!", Toast.LENGTH_SHORT).show();
            firstBackTime = secondBackTime;
            return;
        } else {
            finish();
        }
    }

    public void initRequestPermissions() {//权限列表
        if (!app.hasPermissions) {
            String[] list = new String[]{
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.MODIFY_AUDIO_SETTINGS,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.INSTALL_PACKAGES,
                    Manifest.permission.SET_TIME,
                    Manifest.permission.CALL_PHONE
            };
            if (!EasyPermissions.hasPermissions(this, list)) {
                EasyPermissions.requestPermissions(this, "此应用程序需要权限", 123, list);
            } else {
                app.hasPermissions = true;
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


}
