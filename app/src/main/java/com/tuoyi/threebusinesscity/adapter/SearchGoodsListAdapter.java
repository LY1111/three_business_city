package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.onLineShop.GoodDetailsActivity;
import com.tuoyi.threebusinesscity.bean.SearchGoodsListBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.RushBuyCountDownTimerView;

import java.util.List;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class SearchGoodsListAdapter extends RecyclerView.Adapter<SearchGoodsListAdapter.ViewHolder> {
    private Context context;
    private List<SearchGoodsListBean.DataBean> beanList;

    public SearchGoodsListAdapter(Context context, List<SearchGoodsListBean.DataBean> beanList) {
        this.context = context;
        this.beanList = beanList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder holder = null;
        if (holder == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_search_goods_list, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SearchGoodsListBean.DataBean bean = beanList.get(position);
        holder.mName.setText(bean.getName());
        if (1==bean.getType()){
            holder.timeView1.setVisibility(View.GONE);
            holder.mPrice.setVisibility(View.GONE);
            holder.integral.setText("积分："+bean.getPay_points());
        }else if (2==bean.getType()){
            holder.timeView1.setVisibility(View.GONE);
            holder.mPrice.setText("￥" + bean.getPrice());
            holder.integral.setText("+ "+bean.getPay_points()+"积分");
        }else if (3==bean.getType()){
            holder.timeView1.setVisibility(View.GONE);
            holder.integral.setText("可得"+bean.getPay_points()+"积分");
            holder.mPrice.setText("￥" + bean.getPrice());
        }else {
            holder.mPrice.setVisibility(View.GONE);
            holder.integral.setText("积分："+bean.getPay_points());
             /* 设置倒计时 单位秒 */
            int sum = 6000;
            holder.timeView1.addTime(sum);
            holder.timeView1.start();
        }


        Glide.with(context).load(IMGS + bean.getImage()).into(holder.mImg);
        holder.mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ToastUtil.show(context, String.valueOf(position));
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", String.valueOf(bean.getGoods_id()));
                JumpUtil.newInstance().jumpRight(context, GoodDetailsActivity.class,bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return beanList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public  View                      rootView;
        public  ImageView                 mImg;
        public  TextView                  mName;
        public  TextView                  mPrice;
        public  LinearLayout              mDetails;
        public  TextView                  integral;
        private RushBuyCountDownTimerView timeView1;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.mImg = (ImageView) rootView.findViewById(R.id.mImg);
            this.mName = (TextView) rootView.findViewById(R.id.mName);
            this.mPrice = (TextView) rootView.findViewById(R.id.mPrice);
            this.integral = (TextView) rootView.findViewById(R.id.integral);
            this.mDetails = (LinearLayout) rootView.findViewById(R.id.mDetails);
            this.timeView1=rootView.findViewById(R.id.timeView1);
        }

    }

}
