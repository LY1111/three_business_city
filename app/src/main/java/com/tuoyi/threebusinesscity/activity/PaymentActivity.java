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
import com.tuoyi.threebusinesscity.adapter.SmallBPayAdapter;
import com.tuoyi.threebusinesscity.bean.BusinessPaymentBean;
import com.tuoyi.threebusinesscity.bean.PayNowBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;
import com.wang.avi.AVLoadingIndicatorView;

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
    @BindView(R.id.ll_payment)
    LinearLayout llPayment;
    @BindView(R.id.avi)
    AVLoadingIndicatorView avi;
    private List<PayNowBean> payNowBeanList = new ArrayList<>();
    private SmallBPayAdapter adapter;
    private int num = 2;
    private String where, money, orderno,businessID;

    /**
     * where包括：
     * GeneralDetailsActivity：     商家详情（输入支付）
     * Confirmation_OrderActivity： 确认订单Activity
     * GeneralDetailsAdapte：       商家商品
     * OrederAdapter                我的订单WholeFragment
     *
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        ButterKnife.bind(this);
        initData();
        initRecyclerView();
        Bundle bundle = getIntent().getExtras();
        where = bundle.getString("where");
        money = bundle.getString("money");
        if (!"GeneralDetailsActivity".equals(where)) {
            llPayment.setFocusable(true);
            llPayment.setFocusableInTouchMode(true);
            mPutMoney.setText("¥" + money);
            mPutMoney.setEnabled(false);
        }
        if ("GeneralDetailsAdapte".equals(where)||"GeneralDetailsActivity".equals(where)){
            businessID=bundle.getString("businessID");
        }
        if ("Confirmation_OrderActivity".equals(where) || "OrederAdapter".equals(where)) {
            orderno = bundle.getString("orderno");
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

    @OnClick({R.id.leftBack, R.id.tv_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                finish();
                break;
            case R.id.tv_pay:
                if ("GeneralDetailsActivity".equals(where)) {
                    avi.setVisibility(View.VISIBLE);
                    avi.show();
                    initOkGoList(mPutMoney.getText().toString().trim());
                } else if ("GeneralDetailsAdapte".equals(where)) {
                    avi.setVisibility(View.VISIBLE);
                    avi.show();
                    initOkGoList(money);
                } else if ("Confirmation_OrderActivity".equals(where)) {
                    avi.setVisibility(View.VISIBLE);
                    avi.show();
                    initOkGoList1(money);
                } else if ("OrederAdapter".equals(where)) {
                    avi.setVisibility(View.VISIBLE);
                    avi.show();
                    initOkGoList1(money);
                }
                break;
        }
    }

    //
    private void initOkGoList1(String money) {
        OkGo.<String>post(Config.s + "api/AppProve/personal_commercial_city_payment")
                .tag(this)
                .params("amount", money)
                .params("order_num_alias", orderno)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessPaymentBean bean = gson.fromJson(response.body(), BusinessPaymentBean.class);
                        if (bean.getCode() == 200) {
                            avi.hide();
                            avi.setVisibility(View.GONE);
                            //Toast.makeText(PaymentActivity.this, bean.getMessage(), Toast.LENGTH_SHORT).show();
                            Uri uri = Uri.parse(bean.getData().getPayInfo());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            finish();
                        } else {
                            avi.hide();
                            avi.setVisibility(View.GONE);
                            RxToast.error(bean.getMessage());
                        }
                    }
                });
    }

    private void initOkGoList(String money1) {
        OkGo.<String>post(Config.s + "api/AppProve/business_payment")
                .tag(this)
                .params("amount", money1)
                .params("business_id", businessID)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BusinessPaymentBean bean = gson.fromJson(response.body(), BusinessPaymentBean.class);
                        if (bean.getCode() == 200) {
                            avi.hide();
                            avi.setVisibility(View.GONE);
                            Uri uri = Uri.parse(bean.getData().getPayInfo());
                            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                            startActivity(intent);
                            finish();
                        } else {
                            avi.hide();
                            avi.setVisibility(View.GONE);
                            RxToast.error(bean.getMessage());
                        }
                    }
                });
    }


}
