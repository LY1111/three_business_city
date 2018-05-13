package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.LoginBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CompLoginActivity extends AppCompatActivity {

    @BindView(R.id.login_phone)
    EditText loginPhone;
    @BindView(R.id.login_password)
    EditText loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_bt)
    public void onViewClicked() {
        OkGo.<String>post("http://sszl.tuoee.com/api/member/business_login")
                .params("telephone",loginPhone.getText().toString())
                .params("password",loginPassword.getText().toString())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        LoginBean loginBean = gson.fromJson(response.body(), LoginBean.class);
                        if (loginBean.getCode() == 200) {
                            UserBean.setToken(CompLoginActivity.this, loginBean.getData().getToken());
                            RxActivityTool.skipActivity(CompLoginActivity.this,MainActivity.class);
                        }
                        Toast.makeText(CompLoginActivity.this, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
