package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.andview.refreshview.XRefreshView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MainBottomShopAdapter;
import com.tuoyi.threebusinesscity.bean.MainBottomShopBean;
import com.tuoyi.threebusinesscity.url.Config;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainShopDetailsActivity extends AppCompatActivity {

    @BindView(R.id.mainf_bottom_shop_list)
    RecyclerView mainfBottomShopList;
    @BindView(R.id.mainf_XRefreshView)
    XRefreshView mRefreshView;
    private int id;
    private int pagenow = 1;
    private double mLng,mLat;

    private List<MainBottomShopBean.DataBean> bottomShopList = new ArrayList<>();
    private MainBottomShopAdapter bottomShopAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shop_details);
        ButterKnife.bind(this);
        id = getIntent().getExtras().getInt("id");
        mLat = getIntent().getExtras().getDouble("lat");
        mLng = getIntent().getExtras().getDouble("lon");
        initOkGoList(id);
    }
    private void initOkGoList(int pos) {
        OkGo.<String>post(Config.s + "api/app/get_business_all")
                .tag(this)
                .params("id", pos)
                .params("page", pagenow)
                .params("num", 10)
                .params("lng", mLng)
                .params("lat", mLat)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
//                        bottomShopList = new LinkedList<>();
                        MainBottomShopBean shopBean = gson.fromJson(response.body(), MainBottomShopBean.class);
                        if (shopBean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (pagenow == 1) {
                                bottomShopList = new ArrayList<>();
                                bottomShopList = shopBean.getData();
                            } else {
                                bottomShopList.addAll(shopBean.getData());
                            }
                            mRefreshView.stopRefresh();
                        } else {
//                            Toast.makeText(getContext(), shopBean.getMessage(), Toast.LENGTH_SHORT).show();
                            if (pagenow == 1) {
                                bottomShopList = new LinkedList<>();
                            }
                            mRefreshView.setLoadComplete(true);
                        }

                        initShopList();
                    }
                });
    }

    /**
     * 初始化底部商家列表
     */
    private void initShopList() {
        mainfBottomShopList.setLayoutManager(new LinearLayoutManager(this));
        bottomShopAdapter = new MainBottomShopAdapter(bottomShopList);
        mainfBottomShopList.setAdapter(bottomShopAdapter);
    }

}
