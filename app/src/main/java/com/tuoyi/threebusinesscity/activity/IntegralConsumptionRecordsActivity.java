package com.tuoyi.threebusinesscity.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.IntegralConsumptionRecordsAdapter;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;

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
    RecyclerView mList;
    private List<IntegralConsumptionRecordsBean> beanList;
    private IntegralConsumptionRecordsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integer_consumption_records);
        ButterKnife.bind(this);
        initList();

    }

    /**
     * 积分详情列表
     */
    private void initList() {
        beanList = new ArrayList<>();
        for (int i = 60; i < 66; i++) {
            beanList.add(new IntegralConsumptionRecordsBean(i));
        }
        mList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new IntegralConsumptionRecordsAdapter(beanList);
        mList.setAdapter(mAdapter);
    }

    @OnClick(R.id.integer_consumptionRecord_back)
    public void onViewClicked() {
    }
}
