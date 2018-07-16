package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.PresentRecordListadapter;
import com.tuoyi.threebusinesscity.bean.BankCarkListBean;
import com.tuoyi.threebusinesscity.bean.PresentRecordBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PresentRecordListActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.order_recyclerview)
    RecyclerView orderRecyclerview;
    @BindView(R.id.smartrefreshlayout)
    SmartRefreshLayout smartrefreshlayout;

    private int page = 1;
    private boolean isClear;
    private PresentRecordListadapter adapter;
    private List<PresentRecordBean.DataBean> beanList=new ArrayList<>();
    private String where;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presentrecordlist);
        RxBarTool.setStatusBarColor(this, R.color.colorPrimary);
        ButterKnife.bind(this);
        Bundle bundle=getIntent().getExtras();
        where=bundle.getString("where");
        initRecyclerview();
        initListener();
        if ("会员".equals(where)){
            getPresentRecord();
        }else {
            getPresentRecord1();
        }
    }

    @OnClick(R.id.leftBack)
    public void onViewClicked() {
        finish();
    }

    private void initRecyclerview() {

        orderRecyclerview.setNestedScrollingEnabled(false);
        orderRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PresentRecordListadapter(R.layout.item_presentrecordlist, beanList);
        orderRecyclerview.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("Id", mList.get(position).id);
//                bundle.putInt("auditStatus", auditStatus);
//                readyGo(ApplyDetailsActivity.class, bundle);
            }
        });
    }

    private void initListener() {
        smartrefreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                isClear = true;
                if ("会员".equals(where)){
                    getPresentRecord();
                }else {
                    getPresentRecord1();
                }
                refreshlayout.finishRefresh();
            }
        });
        smartrefreshlayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                isClear = false;
                if ("会员".equals(where)){
                    getPresentRecord();
                }else {
                    getPresentRecord1();
                }
                refreshLayout.finishLoadMore();
            }

        });
    }

    //会员提现
    private void getPresentRecord(){
            OkGo.<String>post(Config.s + "api/AppProve/Membership_list")
                    .tag(this)
                    .params("token", UserBean.getToken(this))
                    .params("num", 10)
                    .params("page", page)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Logger.json(response.body());
                            Gson gson = new Gson();
                            PresentRecordBean msgBean = gson.fromJson(response.body(), PresentRecordBean.class);
                            if (msgBean.getCode() == 200) {
                               if (isClear){
                                   beanList.clear();
                               }
                               beanList.addAll(msgBean.getData());
                               adapter.setNewData(beanList);
                            } else {
                                RxToast.error(msgBean.getMessage());
                            }
                        }
                    });

    }

    //会员提现
    private void getPresentRecord1(){
            OkGo.<String>post(Config.s + "api/AppBusinessProve/membership_list")
                    .tag(this)
                    .params("business_token", UserBean.getBusineToken(this))
                    .params("num", 10)
                    .params("page", page)
                    .execute(new StringCallback() {
                        @Override
                        public void onSuccess(Response<String> response) {
                            Logger.json(response.body());
                            Gson gson = new Gson();
                            PresentRecordBean msgBean = gson.fromJson(response.body(), PresentRecordBean.class);
                            if (msgBean.getCode() == 200) {
                               if (isClear){
                                   beanList.clear();
                               }
                               beanList.addAll(msgBean.getData());
                               adapter.setNewData(beanList);
                            } else {
                                RxToast.error(msgBean.getMessage());
                            }
                        }
                    });

    }
}
