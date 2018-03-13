package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 消费积分详情
 * Created by md
 * on 2018/3/7 0007.
 */

public class IntegralConsumptionRecordsAdapter extends RecyclerView.Adapter<IntegralConsumptionRecordsAdapter.ViewHolder> {

    private Context context;
    private List<IntegralConsumptionRecordsBean> mdatas;

    public IntegralConsumptionRecordsAdapter(List<IntegralConsumptionRecordsBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_integral_consumptiion_records, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.mTvIntegral.setText(String.valueOf(holder.mItem.getIntegral()));
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "可点击的：" + position + "号子布局", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_integral_consumptionRecords_tv_name)
        TextView mTvName;
        @BindView(R.id.item_integral_consumptionRecords_tv_type)
        TextView mTvType;
        @BindView(R.id.item_integral_consumptionRecords_tv_money)
        TextView mTvMoney;
        @BindView(R.id.item_integral_consumptionRecords_tv_integral)
        TextView mTvIntegral;
        @BindView(R.id.item_integral_consumptionRecords_tv_time)
        TextView mTvTime;
        public IntegralConsumptionRecordsBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
