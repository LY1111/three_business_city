package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.tuoyi.threebusinesscity.bean.BaseBean;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.url.Config;
import com.tuoyi.threebusinesscity.widget.AutoLinearLayoutManager;
import com.vondear.rxtools.view.RxToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 积分消费记录
 */
public class IntegralConsumptionRecordsActivity extends AppCompatActivity {

    @BindView(R.id.integer_consumptionRecord_back)
    ImageView mBack;
    @BindView(R.id.integer_consumptionRecord_money)
    TextView mMoney;
    @BindView(R.id.integer_consumptionRecord_integer)
    TextView mInteger;
    @BindView(R.id.integer_consumptionRecord_list)
    RecyclerView mRecyclerview;
    @BindView(R.id.integer_consumptionRecord_title)
    TextView integerConsumptionRecordTitle;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    private List<IntegralConsumptionRecordsBean.DataBean> beanList = new ArrayList<>();
    private IntegralConsumptionRecordsAdapter mAdapter;
    private int             page    = 1;
    private boolean         isClear = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integer_consumption_records);
        ButterKnife.bind(this);

        initRecyclerView();
        //下拉刷新
        initListener();

        okGoRecords();
    }

    private void initRecyclerView() {
        mRecyclerview.setNestedScrollingEnabled(false);
        mRecyclerview.setLayoutManager(new AutoLinearLayoutManager(this));

        mAdapter = new IntegralConsumptionRecordsAdapter(beanList);
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

    @OnClick(R.id.integer_consumptionRecord_back)
    public void onViewClicked() {
        finish();
    }

    private void okGoRecords() {
        OkGo.<String>post(Config.s + "api/AppProve/records_of_consumption")
                .tag(this)
                .params("token", UserBean.getToken(this))
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
                        IntegralConsumptionRecordsBean baseBean = gson.fromJson(response.body(), IntegralConsumptionRecordsBean.class);
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
