package com.tuoyi.threebusinesscity.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.SmallBPayAdapter;
import com.tuoyi.threebusinesscity.bean.BusinessPaymentBean;
import com.tuoyi.threebusinesscity.bean.GeneralDetailsBean;
import com.tuoyi.threebusinesscity.bean.PayNowBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.RxBarTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentActivity extends AppCompatActivity {
    @BindView(R.id.ed_mPutMoney)
    EditText mPutMoney;
    @BindView(R.id.pay_recycleview)
    RecyclerView payRecycleview;
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    private List<PayNowBean> payNowBeanList = new ArrayList<>();
    private SmallBPayAdapter adapter;
    private int num = 2;
    private String where;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        ButterKnife.bind(this);
        initData();
        initRecyclerView();
        Bundle bundle=getIntent().getExtras();
        where=bundle.getString("where");
        if (!"GeneralDetailsActivity".equals(where)){
            mPutMoney.setText(where);
        }

    }

    private void initData() {
        PayNowBean bean = new PayNowBean();
        bean.setNum(1);
        bean.setName("微信支付");
        bean.setChecked(false);
        payNowBeanList.add(bean);
        PayNowBean bean2 = new PayNowBean();
        bean2.setNum(2);
        bean2.setName("支付宝支付");
        bean2.setChecked(true);
        payNowBeanList.add(bean2);
    }

    private void initRecyclerView() {
        payRecycleview.setNestedScrollingEnabled(false);
        payRecycleview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SmallBPayAdapter(R.layout.item_small_b_pay, payNowBeanList);
        payRecycleview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                for (int i = 0; i < payNowBeanList.size(); i++) {
                    if (position == i) {
                        payNowBeanList.get(i).setChecked(true);
                        payNowBeanList.get(i).getName();
                    } else {
                        payNowBeanList.get(i).setChecked(false);
                    }
                }
                adapter.setNewData(payNowBeanList);
            }
        });
    }

    private void initOkGoList(String money) {
        OkGo.<String>post(Config.s + "api/AppProve/business_payment")
                .tag(this)
                .params("amount", money)
                .params("business_id", "88")
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessPaymentBean bean = gson.fromJson(response.body(), BusinessPaymentBean.class);
                        if (bean.getCode() == 200) {
                            Toast.makeText(PaymentActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            Uri uri = Uri.parse(bean.getData().getPayInfo());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(PaymentActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.leftBack, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.tv_pay:
                if ("GeneralDetailsActivity".equals(where)){
                    initOkGoList(mPutMoney.getText().toString().trim());
                }else {
                    initOkGoList(where);
                }
                break;
        }
    }
}
