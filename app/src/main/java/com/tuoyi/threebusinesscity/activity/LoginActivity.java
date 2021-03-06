package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.LoginBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录
 */
public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_bt)
    Button loginBt;
    @BindView(R.id.login_register)
    TextView loginRegister;
    @BindView(R.id.login_forget)
    TextView loginForget;
    @BindView(R.id.login_wechat)
    ImageView loginWechat;
    @BindView(R.id.login_qq)
    ImageView loginQq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        com.pgyersdk.crash.PgyCrashManager.register(this);
        initUpdateApp();
    }

    @OnClick({R.id.login_back, R.id.login_bt, R.id.login_register, R.id.login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                break;
            case R.id.login_bt:
                OkGo.<String>post(Config.s + "api/Member/login")
                        .tag(this)
                        .params("telephone", loginPhone.getText().toString().trim())
                        .params("password", loginPassword.getText().toString().trim())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Logger.json(response.body());
                                Gson gson = new Gson();
                                LoginBean loginBean = gson.fromJson(response.body(), LoginBean.class);
                                if (loginBean.getCode() == 200) {
                                    UserBean.setToken(LoginActivity.this, loginBean.getData().getToken());
                                    RxActivityTool.skipActivity(LoginActivity.this,MainActivity.class);
                                    finish();
                                }
                                Toast.makeText(LoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
//
                            }
                        });
                break;
            case R.id.login_register:
                RxActivityTool.skipActivity(this,RegisterActivity.class);
                break;
            case R.id.login_forget:
                RxActivityTool.skipActivity(this,ForgetPwdActivity.class);
                break;
        }
    }


    private void initUpdateApp() {
        com.pgyersdk.update.PgyUpdateManager.register(LoginActivity.this,
                new com.pgyersdk.update.UpdateManagerListener() {

                    @Override
                    public void onUpdateAvailable(final String result) {
                        // 将新版本信息封装到AppBean中
                        final com.pgyersdk.javabean.AppBean appBean = getAppBeanFromString(result);
                        final com.vondear.rxtools.view.dialog.RxDialogSure rxDialogSure = new com.vondear.rxtools.view.dialog.RxDialogSure(LoginActivity.this);
                        rxDialogSure.setTitle("版本更新");
                        rxDialogSure.setSure("确定更新");
                        rxDialogSure.setContent("发现新版本：" + appBean.getVersionName());
                        rxDialogSure.setSureListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startDownloadTask(
                                        LoginActivity.this,
                                        appBean.getDownloadURL());
                                com.pgyersdk.update.UpdateManagerListener.updateLocalBuildNumber(result);

                            }
                        });
                        rxDialogSure.show();
                    }

                    @Override
                    public void onNoUpdateAvailable() {
                    }
                });
    }
}
