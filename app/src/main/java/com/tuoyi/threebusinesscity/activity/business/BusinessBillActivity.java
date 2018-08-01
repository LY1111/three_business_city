package com.tuoyi.threebusinesscity.activity.business;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
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
import com.tuoyi.threebusinesscity.adapter.BusinessBillAdapter;
import com.tuoyi.threebusinesscity.adapter.IntegralConsumptionRecordsAdapter;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;
import com.tuoyi.threebusinesscity.bean.TradingFlowBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.widget.AutoLinearLayoutManager;
import com.vondear.rxtools.RxActivityTool;
import com.vondear.rxtools.RxBarTool;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BusinessBillActivity extends AppCompatActivity {
    @BindView(R.id.leftBack)
    ImageView leftBack;
    @BindView(R.id.login_title)
    TextView loginTitle;
    @BindView(R.id.businessbill_list)
    RecyclerView mRecyclerview;
    @BindView(R.id.mainf_XRefreshView)
    SmartRefreshLayout refreshLayout;

    private int             page    = 1;
    private boolean         isClear = true;
    private List<TradingFlowBean.DataBean> beanList = new ArrayList<>();
    private BusinessBillAdapter mAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_businessbill);
        RxActivityTool.addActivity(this);
        RxBarTool.setStatusBarColor(this,R.color.colorPrimary);
        ButterKnife.bind(this);

        initRecyclerView();
        //下拉刷新
        initListener();

        okGoRecords();
    }

    @OnClick(R.id.leftBack)
    public void onViewClicked() {
        RxActivityTool.finishActivity(this);
    }

    private void initRecyclerView() {
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new AutoLinearLayoutManager(this));
        mAdapter = new BusinessBillAdapter(R.layout.item_trading_flow,beanList);
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
        OkGo.<String>post(Config.s + "api/AppBusinessProve/bill_flow")
                .tag(this)
                .params("page", page)
                .params("num", "10")
                .params("business_token", UserBean.getBusineToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        TradingFlowBean baseBean = gson.fromJson(response.body(), TradingFlowBean.class);
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
}
