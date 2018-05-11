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
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.tuoyi.threebusinesscity.url.Config.S;

/**
 * 注册
 */
public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.register_back)
    ImageView mBack;
    @BindView(R.id.register_title)
    TextView mTitle;
    @BindView(R.id.register_phone)
    EditText mPhone;
    @BindView(R.id.register_Verification_password)
    EditText mVerificationPassword;
    @BindView(R.id.register_Verification_Code_btn)
    TextView mVerificationCodeBtn;
    @BindView(R.id.register_passA)
    EditText mPassA;
    @BindView(R.id.register_passB)
    EditText mPassB;
    @BindView(R.id.register_userName)
    EditText mUsername;
    @BindView(R.id.register_topPhone)
    EditText mTopPhone;
    @BindView(R.id.register_commit_bt)
    Button mCommitBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

    }
//15631557242
    @OnClick({R.id.register_back,R.id.register_toLogin, R.id.register_Verification_Code_btn, R.id.register_commit_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_toLogin:
                RxActivityTool.skipActivity(this,LoginActivity.class);
                break;
            case R.id.register_back:
                finish();
                break;
            case R.id.register_Verification_Code_btn:
                break;
            case R.id.register_commit_bt:
                OkGo.<String>post(S + "api/member/register")
                        .tag(this)
                        .params("username", mUsername.getText().toString().trim())
                        .params("telephone", mPhone.getText().toString().trim())
                        .params("password", mPassA.getText().toString().trim())
                        .params("referee", mTopPhone.getText().toString().trim())
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Logger.json(response.body());
                                Gson gson = new Gson();
                                LoginBean loginBean = gson.fromJson(response.body(), LoginBean.class);
                                if (loginBean.getCode() == 200) {
                                    UserBean.setToken(RegisterActivity.this, loginBean.getData().getToken());
                                }
                                Toast.makeText(RegisterActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        });


                break;
        }
    }
}
