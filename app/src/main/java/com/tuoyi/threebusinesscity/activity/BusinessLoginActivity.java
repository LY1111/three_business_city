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
import com.tuoyi.threebusinesscity.bean.BusinessLoginBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
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
    @BindView(R.id.business_register)
    TextView businessRegister;
    @BindView(R.id.login_forget)
    TextView loginForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);
    }

    @OnClick({R.id.login_back, R.id.login_bt, R.id.business_register, R.id.login_forget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                break;
            case R.id.login_bt:
                OkGo.<String>post("http://sszl.tuoee.com/api/member/business_login")
                        .tag(this)
                        .params("telephone", loginPhone.getText().toString().trim())
                        .params("password", loginPassword.getText().toString().trim())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Logger.json(response.body());
                                Gson gson = new Gson();
                                BusinessLoginBean loginBean = gson.fromJson(response.body(), BusinessLoginBean.class);
                                if (loginBean.getCode() == 200) {
                                    UserBean.setBusineToken(BusinessLoginActivity.this, loginBean.getData().getBusiness_token());

                                    RxActivityTool.skipActivity(BusinessLoginActivity.this,CoOperatorActivity.class);
                                    RxActivityTool.finishActivity(BusinessLoginActivity.this);
                                }
                                Toast.makeText(BusinessLoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
//
                            }
                        });
                break;
            case R.id.business_register:
                RxActivityTool.skipActivity(this,MyBusinessActivity.class);
                break;
            case R.id.login_forget:
                RxActivityTool.skipActivity(this,ForgetPwdActivity.class);
                break;
        }
    }
}
