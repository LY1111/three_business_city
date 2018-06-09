package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 登录
 */
public class BusinessLoginActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_business_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_back, R.id.login_bt, R.id.login_register, R.id.login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                break;
            case R.id.login_bt:
               /* OkGo.<String>post(Config.s + "api/Member/login")
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
                                    UserBean.setToken(BusinessLoginActivity.this, loginBean.getData().getToken());
                                    RxActivityTool.skipActivity(BusinessLoginActivity.this,MainActivity.class);
                                    finish();
                                }
                                Toast.makeText(BusinessLoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
//
                            }
                        });*/
                break;
            case R.id.login_register:
                RxActivityTool.skipActivity(this,MyBusinessActivity.class);
                break;
            case R.id.login_forget:
                RxActivityTool.skipActivity(this,ForgetPwdActivity.class);
                break;
        }
    }
}