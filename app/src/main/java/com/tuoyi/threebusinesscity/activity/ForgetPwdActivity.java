package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.CommonUtils;
import com.tuoyi.threebusinesscity.util.CountDownTimerUtils;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @创建者 Liyan
 * @创建时间 2018/1/17 17:22
 * @描述 ${忘记密码}
 */

public class ForgetPwdActivity extends AppCompatActivity {


    @BindView(R.id.leftBack)
    ImageView mLoginBack;
    @BindView(R.id.login_title)
    TextView mLoginTitle;
    @BindView(R.id.tv_star)
    TextView mTvStar;
    @BindView(R.id.tv_phone_num)
    TextView mTvPhoneNum;
    @BindView(R.id.et_phone_num)
    EditText mEtPhoneNum;
    @BindView(R.id.iv_phone_num_error)
    ImageView mIvPhoneNumError;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    @BindView(R.id.tv_phone_num_error)
    TextView mTvPhoneNumError;
    @BindView(R.id.tv_star2)
    TextView mTvStar2;
    @BindView(R.id.tv_VerificationCode)
    TextView mTvVerificationCode;
    @BindView(R.id.et_verification_code)
    EditText mEtVerificationCode;
    @BindView(R.id.btn_get_yzm)
    Button mBtnGetYzm;
    @BindView(R.id.et_pwd)
    EditText mEtPwd;
    @BindView(R.id.btn_quit)
    Button mBtnQuit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        initViewsAndEvents();
    }


    private void initViewsAndEvents() {
        mEtPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (i > 9) {
                    mBtnGetYzm.setEnabled(true);
                } else {
                    mBtnGetYzm.setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick({R.id.btn_get_yzm, R.id.btn_quit, R.id.leftBack})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_get_yzm:
                if (isPhoneNmb()) {
                    mTvPhoneNumError.setVisibility(View.GONE);
                    mIvPhoneNumError.setVisibility(View.GONE);
                    CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(mBtnGetYzm, 60000, 1000);
                    countDownTimerUtils.start();
                    //获取验证码
                    getRandomNum();
                } else {
                    mTvPhoneNumError.setVisibility(View.VISIBLE);
                    mIvPhoneNumError.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.btn_quit:
                if (isPhoneNmb() && isVerificationCode() && isPwdValid()) {
                    ChangePWD();
                }
                break;
            case R.id.leftBack:
                finish();
                break;
        }
    }


    /**
     * 获取验证码
     */
    private void getRandomNum() {
        if (isPhoneNmb()) {
            OkGo.<String>post(Config.s + "api/App/verification_code")
                    .tag(this)
                    .params("phone", mEtPhoneNum.getText().toString().trim())
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Logger.json(response.body());
                            Gson gson = new Gson();
                            BaseBean loginBean = gson.fromJson(response.body(), BaseBean.class);
                            if (loginBean.getCode() == 200) {
                            }
                            Toast.makeText(ForgetPwdActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    /**
     * 忘记密码
     */
    private void ChangePWD() {

        OkGo.<String>post(Config.s + "api/member/forget_password")
                .tag(this)
                .params("telephone", mEtPhoneNum.getText().toString().trim())
                .params("password", mEtPwd.getText().toString().trim())
                .params("verification_code", mEtVerificationCode.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean baseBean = gson.fromJson(response.body(), BaseBean.class);
                        if (baseBean.getCode() == 200) {
                            UserBean.setToken(ForgetPwdActivity.this, "");
                            findPwdSucc();
                            finish();
                        }
                        //Toast.makeText(ForgetPwdActivity.this, baseBean.getMessage(), Toast.LENGTH_SHORT).show();
//
                    }
                });
    }


    public String getPhoneNum() {
        return mEtPhoneNum.getText().toString();
    }


    public String getPwd() {
        return mEtPwd.getText().toString();
    }


    /*public String getEnterPwd() {
        return mEtPwd2.getText().toString();
    }*/


    public String getVerificationCode() {
        return mEtVerificationCode.getText().toString();
    }


    public void findPwdSucc() {
        ToastUtil.show(ForgetPwdActivity.this, "密码已找回，请用新密码登陆");
    }

    private boolean isPhoneNmb() {
        if (TextUtils.isEmpty(getPhoneNum().trim())) {
            ToastUtil.show(ForgetPwdActivity.this, "请输入手机号");
            return false;
        }
        if (!CommonUtils.isMobilePhone(getPhoneNum())) {
            ToastUtil.show(ForgetPwdActivity.this, "请输入正确手机号");
            return false;
        }
        return true;
    }

    private boolean isVerificationCode() {
        if (TextUtils.isEmpty(getVerificationCode().trim())) {
            ToastUtil.show(ForgetPwdActivity.this, "请输入验证码");
            return false;
        }
        return true;
    }

    private boolean isPwdValid() {
        if (TextUtils.isEmpty(getPwd().trim())) {
            ToastUtil.show(ForgetPwdActivity.this, "请输入密码");
            return false;
        }
        return true;
    }



  /*  private boolean isPwd2Valid() {
        if (TextUtils.isEmpty(getEnterPwd().trim())) {
            ToastUtil.show(ForgetPwdActivity.this, "请输入确认密码");
            return false;
        }
        if (!getPwd().equals(getEnterPwd())) {
            ToastUtil.show(ForgetPwdActivity.this, "密码已找回，两次密码不一致");
            return false;
        }
        return true;
    }*/
}
