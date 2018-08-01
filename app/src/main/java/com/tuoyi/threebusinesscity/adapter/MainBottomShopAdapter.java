package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.GeneralDetailsActivity;
import com.tuoyi.threebusinesscity.bean.MainBottomShopBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import java.math.BigDecimal;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by md
 * 首页底部商家列表适配器
 * on 2018/3/3 0003.
 */

public class MainBottomShopAdapter extends RecyclerView.Adapter<MainBottomShopAdapter.ViewHolder> {

    private Context context;
    private List<MainBottomShopBean.DataBean> mdatas;

    public MainBottomShopAdapter(List<MainBottomShopBean.DataBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_bottom_shop, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.itemName.setText(holder.mItem.getShop_name());
        if (holder.mItem.getDistance()>1000){
            holder.itemDistance.setText("距离：" + new BigDecimal(holder.mItem.getDistance()).divide(new BigDecimal(1000)).setScale(2, BigDecimal.ROUND_DOWN)  + "km");
        }else {
            holder.itemDistance.setText("距离：" +  new BigDecimal(holder.mItem.getDistance()).setScale(2, BigDecimal.ROUND_DOWN) + "m");
        }
        holder.itemContent.setText("会员到店消费奖励金额的" + holder.mItem.getMember() + "%积分");
        holder.itemAddress.setText(holder.mItem.getAddress());
        Glide.with(context).load(IMGS + holder.mItem.getImage()).into(holder.itemIcon);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "可点击的：" + position + "号子布局", Toast.LENGTH_SHORT).show();
                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_main_bottom_shop_icon)
        ImageView itemIcon;
        @BindView(R.id.item_main_grid_menu_name)
        TextView itemName;
        @BindView(R.id.item_main_grid_menu_distance)
        TextView itemDistance;
        @BindView(R.id.item_main_grid_menu_content)
        TextView itemContent;
        @BindView(R.id.item_main_grid_menu_address)
        TextView itemAddress;
        public MainBottomShopBean.DataBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
