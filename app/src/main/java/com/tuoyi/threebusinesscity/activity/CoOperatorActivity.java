package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.business.BusinessAdministrationActivity;
import com.tuoyi.threebusinesscity.activity.business.BusinessBillActivity;
import com.tuoyi.threebusinesscity.activity.business.BusinessReceivableActivity;
import com.tuoyi.threebusinesscity.activity.business.BusinessSetActivity;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.BusinessMsgBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.ToastUtil;
import com.vondear.rxtools.view.RxToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 合作商家入口
 */
public class CoOperatorActivity extends AppCompatActivity {

    @BindView(R.id.CoOperator_img_head)
    CircleImageView imgHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_jinpai)
    TextView tvJinpai;
    @BindView(R.id.tv_turnover)
    TextView tvTurnover;
    @BindView(R.id.tv_Authentication)
    TextView tv_Authentication;
    @BindView(R.id.tv_Contract)
    TextView tv_Contract;
    @BindView(R.id.ll_setshop)
    LinearLayout llSetshop;
    @BindView(R.id.ll_bill)
    LinearLayout bill;
    @BindView(R.id.ll_administration)
    LinearLayout llAdministration;
    @BindView(R.id.ll_Receivables)
    LinearLayout llReceivables;
    @BindView(R.id.ll_PutForward)
    LinearLayout ll_PutForward;
    @BindView(R.id.ll_BankCard)
    LinearLayout ll_BankCard;
    @BindView(R.id.Sign_out)
    TextView sign_out;

    private String business_type;
    private String money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperator);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getBusinessMsg();
        Log.e("adasdasdasdasd","onResume");
    }

    @OnClick({R.id.ll_setshop, R.id.ll_bill, R.id.ll_administration, R.id.ll_Receivables,R.id.Sign_out,R.id.tv_Authentication,R.id.tv_Contract,R.id.ll_PutForward,R.id.ll_BankCard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_setshop://店铺设置
                RxActivityTool.skipActivity(CoOperatorActivity.this,BusinessSetActivity.class);
                break;
            case R.id.ll_bill://账单流水
                RxActivityTool.skipActivity(CoOperatorActivity.this,BusinessBillActivity.class);
                break;
            case R.id.ll_administration://商品管理
                RxActivityTool.skipActivity(CoOperatorActivity.this,BusinessAdministrationActivity.class);
                break;
            case R.id.ll_Receivables://收款码
                RxActivityTool.skipActivity(CoOperatorActivity.this,BusinessReceivableActivity.class);
                break;
            case R.id.Sign_out://退出
                if ("登陆".equals(sign_out.getText().toString())){
                    RxActivityTool.skipActivity(this, BusinessLoginActivity.class);
                }else {
                    UserBean.setBusineToken(CoOperatorActivity.this,"");
                    finish();
                }
                break;
            case R.id.tv_Contract://签约
                Bundle bundle=new Bundle();
                bundle.putString("where","CoOperatorActivity");
                RxActivityTool.skipActivity(CoOperatorActivity.this, ContractActivity.class,bundle);   //签约
                break;
            case R.id.tv_Authentication:
                if ("2".equals(business_type)){             //企业认证
                    RxActivityTool.skipActivity(this, EnterpriseCertificationActivity.class);
                }else if ("3".equals(business_type)){       //个体认证
                    authentication();
            }
                break;
            case R.id.ll_PutForward:    //提现
                Bundle bundle2=new Bundle();
                bundle2.putString("money",money);
                bundle2.putString("type",business_type);
                RxActivityTool.skipActivity(this, ShopsPutForwardActivity.class,bundle2);
                break;
            case R.id.ll_BankCard:
                Bundle bundle1=new Bundle();
                bundle1.putString("where","CoOperatorActivity");
                RxActivityTool.skipActivity(this, BankCardActivity.class,bundle1);
                break;
        }
    }

    //获取店铺信息
    private void getBusinessMsg() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/get_business")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("type", "0")
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessMsgBean msgBean = gson.fromJson(response.body(), BusinessMsgBean.class);
                        if (msgBean.getCode() == 200) {
                            sign_out.setText("退出");

                            RequestOptions options = new RequestOptions();
                            options.placeholder(R.mipmap.headimg);
                            Glide.with(CoOperatorActivity.this).load(Config.IMGS+msgBean.getData().getImage()).apply(options).into(imgHead);
                            tvName.setText(msgBean.getData().getShop_name());
                            money=msgBean.getData().getTotal_bonus()+"";
                            tvTurnover.setText(money);
                            business_type=msgBean.getData().getBusiness_type();
                            if ("3".equals(business_type)){
                                ll_BankCard.setVisibility(View.VISIBLE);
                            }
                            //企业
                            if ("2".equals(business_type)){
                                if ("1".equals(msgBean.getData().getEnterprise_audit())){
                                    tv_Authentication.setText("已审核(企业)");
                                    tv_Authentication.setEnabled(false);
                                }else {
                                    tv_Authentication.setText("去审核");
                                    tv_Authentication.setEnabled(true);
                                }
                                if ("1".equals(msgBean.getData().getElectronic_signing())){
                                    tv_Contract.setText("已签约(企业)");
                                    tv_Contract.setEnabled(false);
                                }else {
                                    tv_Contract.setText("去签约");
                                    tv_Contract.setEnabled(true);
                                }
                            }else {//个人
                                if ("1".equals(msgBean.getData().getIs_real_name())){
                                    tv_Authentication.setText("已认证(个体)");
                                    tv_Authentication.setEnabled(false);
                                }else {
                                    tv_Authentication.setText("去认证");
                                    tv_Authentication.setEnabled(true);
                                }
                                if ("1".equals(msgBean.getData().getElectronic_signing())){
                                    tv_Contract.setText("已签约(个体)");
                                    tv_Contract.setEnabled(false);
                                }else {
                                    tv_Contract.setText("去签约");
                                    tv_Contract.setEnabled(true);
                                }
                            }

                        }else if (msgBean.getCode() == 400){
                            sign_out.setText("登陆");
                            RxToast.error(msgBean.getMessage());
                        }
                    }
                });
    }

    //商家实名认证
    private void authentication() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();

        View view = View.inflate(this, R.layout.dialog_authentication, null);
        dialog.setView(view, 0, 0, 0, 0);// 设置边距为0,保证在2.x的版本上运行没问题

        final EditText etPassword = (EditText) view
                .findViewById(R.id.et_password);
        final EditText etName = (EditText) view
                .findViewById(R.id.et_name);

        Button btnOK = (Button) view.findViewById(R.id.btn_ok);
        Button btnCancel = (Button) view.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String password = etPassword.getText().toString().trim();
                String name = etName.getText().toString().trim();
                // password!=null && !password.equals("")
                if (!TextUtils.isEmpty(password)) {
                    OkGo.<String>post(Config.s + "api/AppBusinessProve/real_name_authentication")
                            .tag(this)
                            .params("name", name)
                            .params("identityNo", password)
                            .params("business_token",  UserBean.getBusineToken(CoOperatorActivity.this))
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Gson gson = new Gson();
                                    BaseBean bean = gson.fromJson(response.body(), BaseBean.class);
                                    if (bean.getCode()==200){
                                        ToastUtil.show(CoOperatorActivity.this, bean.getMessage());
                                        getBusinessMsg();
                                        dialog.dismiss();
                                    }else {
                                        ToastUtil.show(CoOperatorActivity.this, bean.getMessage());
                                    }
                                }
                            });
                } else {
                    Toast.makeText(CoOperatorActivity.this, "身份证号内容不能为空!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialog.dismiss();// 隐藏dialog
            }
        });

        dialog.show();
    }
}
