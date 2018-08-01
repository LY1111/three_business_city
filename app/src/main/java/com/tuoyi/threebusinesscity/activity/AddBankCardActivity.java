package com.tuoyi.threebusinesscity.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.AddBankCarkBean;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddBankCardActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.personalData_save)
    TextView personalDataSave;
    @BindView(R.id.et_cardnum)
    EditText etCardnum;
    @BindView(R.id.et_cardphnum)
    EditText etCardphnum;
    @BindView(R.id.tv_addcard)
    TextView tvAddcard;
    @BindView(R.id.et_PayLineNumber)
    EditText etPayLineNumber;
    @BindView(R.id.et_OpeningBank)
    EditText etOpeningBank;

    private AddBankCarkBean msgBean;
    private String where;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addankcard);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        where = bundle.getString("where");
        LogUtils.e(where);
    }

    @OnClick({R.id.leftBack, R.id.tv_addcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.tv_addcard:
                //先验证银行卡是否可用，验证后走添加银行卡接口
                verificationBankCark();
                break;
        }
    }

    //验证添银行卡是否可用
    private void verificationBankCark() {
        OkGo.<String>post(Config.s + "api/App/verifying_bank_card")
                .tag(this)
                .params("cardNo", etCardnum.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean baseBean = gson.fromJson(response.body(), BaseBean.class);
                        if (baseBean.getCode() == 200) {
                            if ("Personal_InformationActivity".equals(where)) {
                                //会员请求绑定银行卡
                                addBankCark();
                            } else {
                                addBankCark1();
                            }
                        } else {
                            Toast.makeText(AddBankCardActivity.this, baseBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //会员请求绑定银行卡
    private void addBankCark() {
        OkGo.<String>post(Config.s + "api/AppProve/request_binding_bank_card")
                .tag(this)
                .params("cardNo", etCardnum.getText().toString().trim())
                .params("phone", etCardphnum.getText().toString().trim())
                .params("unionBank", etPayLineNumber.getText().toString().trim())
                .params("bankName", etOpeningBank.getText().toString().trim())
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        msgBean = gson.fromJson(response.body(), AddBankCarkBean.class);
                        if (msgBean.getCode() == 200) {
//                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                            myDialog();
                        } else {

                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //个企请求绑定银行卡
    private void addBankCark1() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/request_binding_bank_card")
                .tag(this)
                .params("cardNo", etCardnum.getText().toString().trim())
                .params("phone", etCardphnum.getText().toString().trim())
                .params("business_token", UserBean.getBusineToken(this))
                .params("unionBank", etPayLineNumber.getText().toString().trim())
                .params("bankName",etOpeningBank.getText().toString().trim() )
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        msgBean = gson.fromJson(response.body(), AddBankCarkBean.class);
                        if (msgBean.getCode() == 200) {
//                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                            myDialog();
                        } else {

                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void myDialog() {
        final Dialog bottomDialog = new Dialog(this);
        View contentView = LayoutInflater.from(this).inflate(R.layout.verification_code_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
//        params.width = getResources().getDisplayMetrics().widthPixels - CommonUtils.dp2px(getContext(), 80f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(false);
        bottomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bottomDialog.show();

        final EditText tv_verificationcode = bottomDialog.findViewById(R.id.et_verificationcode);
        final TextView tv_cancle = bottomDialog.findViewById(R.id.tv_cancle);
        tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = tv_verificationcode.getText().toString().trim();
                //String name = etName.getText().toString().trim();
                if (!TextUtils.isEmpty(code)) {
                    if ("Personal_InformationActivity".equals(where)) {
                        confirmBankCark(code);
                    } else {
                        confirmBankCark1(code);
                    }
                } else {
                    RxToast.error("验证码不能为空");
                }
                bottomDialog.dismiss();
            }
        });
    }

    //会员确认绑定银行卡
    private void confirmBankCark(String Code) {
        OkGo.<String>post(Config.s + "api/AppProve/confirm_binding_bank_card")
                .tag(this)
                .params("tranceNum", msgBean.getData().getTranceNum())
                .params("transDate", msgBean.getData().getTransDate())
                .params("phone", etCardphnum.getText().toString().trim())
                .params("verificationCode", Code)
                .params("token", UserBean.getToken(this))
                .params("unionBank", etPayLineNumber.getText().toString().trim())
                .params("bankName", etOpeningBank.getText().toString().trim())
                .params("cardNo", etCardnum.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        AddBankCarkBean msgBean = gson.fromJson(response.body(), AddBankCarkBean.class);
                        if (msgBean.getCode() == 200) {
                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //个企确认绑定银行卡
    private void confirmBankCark1(String Code) {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/confirm_binding_bank_card")
                .tag(this)
                .params("tranceNum", msgBean.getData().getTranceNum())
                .params("transDate", msgBean.getData().getTransDate())
                .params("phone", etCardphnum.getText().toString().trim())
                .params("verificationCode", Code)
                .params("business_token", UserBean.getBusineToken(this))
                .params("unionBank", etPayLineNumber.getText().toString().trim())
                .params("bankName", etOpeningBank.getText().toString().trim())
                .params("cardNo", etCardnum.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        AddBankCarkBean msgBean = gson.fromJson(response.body(), AddBankCarkBean.class);
                        if (msgBean.getCode() == 200) {
                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            Toast.makeText(AddBankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
