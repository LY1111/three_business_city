package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.utils.LogUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.BankCardListAdapter;
import com.tuoyi.threebusinesscity.bean.AddBankCarkBean;
import com.tuoyi.threebusinesscity.bean.BankCarkListBean;
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.util.RxActivityTool;
import com.tuoyi.threebusinesscity.util.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BankCardActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.card_recyclerview)
    RecyclerView cardRecyclerview;
    @BindView(R.id.tv_addcard)
    TextView tvAddcard;
    private String where;
    private BankCardListAdapter adapter;
    private List<BankCarkListBean.DataBean> listBeans;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bankcard);
        com.vondear.rxtools.RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);
        RxActivityTool.addActivity(this);

        Bundle bundle=getIntent().getExtras();
        where=bundle.getString("where");
        LogUtils.e(where);
        initRecyclerView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if ("Personal_InformationActivity".equals(where)){
            getBankCardList();
        }else {
            getBankCardList1();
        }
    }

    @OnClick({R.id.leftBack, R.id.tv_addcard})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.leftBack:
                RxActivityTool.finishActivity(this);
                break;
            case R.id.tv_addcard:
                Bundle bundle =new Bundle();
                bundle.putString("where",where);
                RxActivityTool.skipActivity(this, AddBankCardActivity.class,bundle);
                break;
        }
    }

    private void initRecyclerView(){
        cardRecyclerview.setNestedScrollingEnabled(false);
        cardRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter=new BankCardListAdapter(R.layout.item_bankcard,listBeans);
        cardRecyclerview.setAdapter(adapter);
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if ("Personal_InformationActivity".equals(where)){
                    deleteBankCard(listBeans.get(position).getBankCardNo());
                }else {
                    deleteBankCard1(listBeans.get(position).getBankCardNo());
                }
                listBeans.remove(position);
                adapter.setNewData(listBeans);
            }
        });
    }

    private void getBankCardList(){
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
                            listBeans=new ArrayList<>();
                            listBeans.addAll(msgBean.getData());
                            adapter.setNewData(listBeans);
                        } else {
                            Toast.makeText(BankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void getBankCardList1() {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/all_bank_cards")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        BankCarkListBean msgBean = gson.fromJson(response.body(), BankCarkListBean.class);
                        if (msgBean.getCode() == 200) {
                            listBeans=new ArrayList<>();
                            listBeans.addAll(msgBean.getData());
                            adapter.setNewData(listBeans);
                        } else {

                            Toast.makeText(BankCardActivity.this, msgBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    //解绑银行卡
    private void deleteBankCard(String cardNo) {
        OkGo.<String>post(Config.s + "api/AppProve/untie_bank_card")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("cardNo",cardNo )
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

    //解绑银行卡
    private void deleteBankCard1(String cardNo) {
        OkGo.<String>post(Config.s + "api/AppBusinessProve/untie_bank_card")
                .tag(this)
                .params("business_token", UserBean.getBusineToken(this))
                .params("cardNo",cardNo )
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
