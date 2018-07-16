package com.tuoyi.threebusinesscity.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.PutForwardAdapter;
import com.tuoyi.threebusinesscity.bean.BankCarkListBean;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.PutForwardBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.CommonUtils;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PutForwardActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.tv_Record)
    TextView tvRecord;
    @BindView(R.id.tv_money)
    TextView tvMoney;
    @BindView(R.id.tv_BankName)
    TextView tvBankName;
    @BindView(R.id.tv_BankCard)
    TextView tvBankCard;
    @BindView(R.id.ll_Bank)
    LinearLayout llBank;
    @BindView(R.id.et_money)
    EditText etMoney;
    @BindView(R.id.tv_putforward)
    TextView tvPutforward;

    private PutForwardAdapter adapter;
    private List<BankCarkListBean.DataBean> listBeans= new ArrayList<>();
    private String bankcard,money;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_putforward);
        ButterKnife.bind(this);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        Bundle bundle=getIntent().getExtras();
        money=bundle.getString("money");
        tvMoney.setText("¥"+money);
        okGoBankList();
    }

    @OnClick({R.id.leftBack, R.id.ll_Bank, R.id.tv_Record,R.id.tv_putforward})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.ll_Bank:
                if (listBeans.size()>0){
                    bankCardListDialog();
                }else {
                    RxToast.error("没有绑定银行卡");
                }
                break;
            case R.id.tv_Record:
                //提现记录
                Bundle bundle=new Bundle();
                bundle.putString("where","会员");
                RxActivityTool.skipActivity(this, PresentRecordListActivity.class,bundle);
                break;
            case R.id.tv_putforward:
                if (Double.parseDouble(money)>99){
                    if (Double.parseDouble(etMoney.getText().toString().trim())>99){
                        okGoPutForWard();
                    }else {
                        RxToast.error("提现金额必须大于100元");
                    }
                }else {
                    RxToast.error("余额必须大于100元");
                }
                break;
        }
    }

    private void bankCardListDialog() {
        final Dialog bottomDialog = new Dialog(PutForwardActivity.this);
        View contentView = LayoutInflater.from(PutForwardActivity.this).inflate(R.layout.put_forward_dialog, null);
        bottomDialog.setContentView(contentView);
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) contentView.getLayoutParams();
        params.width = getResources().getDisplayMetrics().widthPixels - CommonUtils.dp2px(this, 15f);
        contentView.setLayoutParams(params);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        bottomDialog.setCanceledOnTouchOutside(false);
        bottomDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        bottomDialog.show();

        final RecyclerView bankcard_list = bottomDialog.findViewById(R.id.bankcard_list);
        bankcard_list.setNestedScrollingEnabled(false);
        bankcard_list.setLayoutManager(new LinearLayoutManager(PutForwardActivity.this));
        adapter = new PutForwardAdapter(R.layout.item_putforward, listBeans);
        bankcard_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i <listBeans.size() ; i++) {
                    if (position==i){
                        listBeans.get(i).setChecked(true);
                    }else {
                        listBeans.get(i).setChecked(false);
                    }
                }
                tvBankName.setText(listBeans.get(position).getBankName());
                tvBankCard.setText("（" + listBeans.get(position).getBankCardNo().substring(15) + "）");
                bankcard=listBeans.get(position).getBankCardNo();
                bottomDialog.dismiss();
            }
        });


    }

    //获取银行卡列表
    private void okGoBankList() {
        OkGo.<String>post(Config.s + "api/AppProve/all_bank_cards")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BankCarkListBean msgBean = gson.fromJson(response.body(), BankCarkListBean.class);
                        if (msgBean.getCode() == 200) {
                            listBeans.clear();
                            listBeans.addAll(msgBean.getData());
                            listBeans.get(0).setChecked(true);
                            tvBankName.setText(listBeans.get(0).getBankName());
                            tvBankCard.setText("（" + listBeans.get(0).getBankCardNo().substring(15) + "）");
                            bankcard=listBeans.get(0).getBankCardNo();
                        } else {
                            //Toast.makeText(PutForwardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                            RxToast.error(msgBean.getMessage());
                        }
                    }
                });

    }

    //提现
    private void okGoPutForWard() {
        OkGo.<String>post(Config.s + "api/AppProve/membership_application")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("bankCardNo", bankcard)
                .params("amount",etMoney.getText().toString().trim())
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        PutForwardBean msgBean = gson.fromJson(response.body(), PutForwardBean.class);
                        if (msgBean.getCode() == 200) {
                            //确认提现弹窗
                            myDialog(msgBean.getData().getBizOrderNo());
                        } else {
                            Toast.makeText(PutForwardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void myDialog(final String bizOrderNo) {
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
                    //if ("Personal_InformationActivity".equals(where)){
                       // confirmBankCark(code);
                    //}else {
                    confirmPutForWard(code,bizOrderNo);
                   // }
                } else {
                    RxToast.error("验证码不能为空");
                }
                bottomDialog.dismiss();
            }
        });
    }

    //提现
    private void confirmPutForWard(String code,String bizOrderNo) {
        OkGo.<String>post(Config.s + "api/AppProve/confirmation_payment")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("bizOrderNo", bizOrderNo)
                .params("verificationCode",code)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BaseBean msgBean = gson.fromJson(response.body(), BaseBean.class);
                        if (msgBean.getCode() == 200) {
                            RxToast.success(msgBean.getMessage());
                        } else {
                            RxToast.error(msgBean.getMessage());
                        }
                    }
                });

    }
}
