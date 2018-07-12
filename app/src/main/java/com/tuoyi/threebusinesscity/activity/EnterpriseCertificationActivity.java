package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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
import com.tuoyi.threebusinesscity.activity.business.BusinessSetActivity;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.BusinessMsgBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxBarTool;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EnterpriseCertificationActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.tv_enterpriseName)
    EditText tvEnterpriseName;
    @BindView(R.id.tv_Name)
    EditText tvName;
    @BindView(R.id.tv_phoneNum)
    EditText tvPhoneNum;
    @BindView(R.id.tv_account)
    EditText tvAccount;
    @BindView(R.id.tv_bankname)
    EditText tvBankname;
    @BindView(R.id.tv_cardNo)
    EditText tvCardNo;
    @BindView(R.id.tv_sub)
    TextView tvSub;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enterprise_certification);
        com.vondear.rxtools.RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        RxActivityTool.addActivity(this);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.leftBack, R.id.tv_sub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;
            case R.id.tv_sub:
                setEnterpriseMsg();
                break;
        }
    }

    //上传企业信息
    private void setEnterpriseMsg() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/setting_up_enterprise_information")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("companyName",tvEnterpriseName.getText().toString().trim() )
                .params("legalName", tvName.getText().toString().trim())
                .params("legalIds", tvCardNo.getText().toString().trim())
                .params("legalPhone", tvPhoneNum.getText().toString().trim())
                .params("accountNo", tvAccount.getText().toString().trim())
                .params("parentBankName", tvBankname.getText().toString().trim())

                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean msgBean = gson.fromJson(response.body(), BaseBean.class);
                        if (msgBean.getCode() == 200) {
                            RxToast.success(msgBean.getMessage());
                            RxActivityTool.finishActivity(EnterpriseCertificationActivity.this);
                        }else {
                            RxToast.error(msgBean.getMessage());
                        }

                    }
                });
    }
}
