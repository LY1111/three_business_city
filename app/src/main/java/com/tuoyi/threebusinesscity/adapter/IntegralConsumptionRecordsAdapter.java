package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.IntegralConsumptionRecordsBean;
import com.vondear.rxtools.RxDataTool;
import com.vondear.rxtools.RxTimeTool;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private List<IntegralConsumptionRecordsBean.DataBean> mdatas;

    public IntegralConsumptionRecordsAdapter(List<IntegralConsumptionRecordsBean.DataBean> items) {
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
       // holder.mTvIntegral.setText(String.valueOf(holder.mItem.getIntegral()));
        holder.mTvName.setText(holder.mItem.getBusiness_shop_name());
        holder.mTvMoney.setText("消费金额：￥"+new BigDecimal(holder.mItem.getMoney()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN));
        holder.mTvTime.setText(stampToDate(holder.mItem.getOrder_time()+""));
        holder.mTvIntegral.setText("+"+holder.mItem.getReturn_points());
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
        public IntegralConsumptionRecordsBean.DataBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

    /*

     * 将时间戳转换为时间

     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s+"000");
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;

    }

}
