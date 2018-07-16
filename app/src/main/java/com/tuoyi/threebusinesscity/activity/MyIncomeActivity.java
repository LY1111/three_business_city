package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.tuoyi.threebusinesscity.adapter.IntegralConsumptionRecordsAdapter;
import com.tuoyi.threebusinesscity.adapter.MyIncomeAdapter;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;
import com.tuoyi.threebusinesscity.bean.MyIncomeBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.widget.AutoLinearLayoutManager;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyIncomeActivity extends AppCompatActivity {
    @BindView(R.id.integer_consumptionRecord_back)
    ImageView integerConsumptionRecordBack;
    @BindView(R.id.integer_consumptionRecord_title)
    TextView integerConsumptionRecordTitle;
    @BindView(R.id.recyclerview_list)
    RecyclerView mRecyclerview;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;

    private List<MyIncomeBean.DataBean> beanList = new ArrayList<>();
    private MyIncomeAdapter mAdapter;
    private int             page    = 1;
    private boolean         isClear = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myincome);
        ButterKnife.bind(this);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);

        initRecyclerView();
        //下拉刷新
        initListener();

        okGoRecords();
    }

    private void initRecyclerView() {
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new AutoLinearLayoutManager(this));

        mAdapter = new MyIncomeAdapter(R.layout.item_myincome,beanList);
        mRecyclerview.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//
//                Intent intent=new Intent(NewsListActivity.this,NewsDetailsActivity.class);
//                intent.putExtra("id",mList.get(position).id);
//                startActivity(intent);
//            }
//        });
    }

    private void initListener() {

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                isClear = true;
                okGoRecords();

                refreshlayout.finishRefresh();
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                isClear = false;
                page++;

                refreshlayout.finishLoadMore();
            }
        });
    }

    private void okGoRecords() {
        OkGo.<String>post(Config.s + "api/AppProve/income")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .params("page", page)
                .params("num", 10)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        MyIncomeBean baseBean = gson.fromJson(response.body(), MyIncomeBean.class);
                        if (baseBean.getCode() == 200) {
                            if (isClear){
                                beanList.clear();
                            }
                            beanList.addAll(baseBean.getData());
                            mAdapter.notifyDataSetChanged();

                        } else {
                            RxToast.error(baseBean.getMessage());
                        }
                    }
                });
    }


    @OnClick(R.id.integer_consumptionRecord_back)
    public void onViewClicked() {
        finish();
    }
}
