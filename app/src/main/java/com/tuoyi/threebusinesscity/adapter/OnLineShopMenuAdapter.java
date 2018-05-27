package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.onLineShop.SearchGoodsListActivity;
import com.tuoyi.threebusinesscity.bean.OnLineShopMenuBean;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by wushishuo on 2018/5/26.
 */

public class OnLineShopMenuAdapter extends RecyclerView.Adapter<OnLineShopMenuAdapter.ViewHolder> {
    private Context context;
    private List<OnLineShopMenuBean.DataBean> mdatas;

    private MyOnItemClickListener itemClickListener;

    public OnLineShopMenuAdapter(Context context, List<OnLineShopMenuBean.DataBean> mdatas) {
        this.context = context;
        this.mdatas = mdatas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = null;
        View view = null;
        if (viewHolder == null) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_main_grid_menu, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final OnLineShopMenuBean.DataBean dataBean = mdatas.get(position);
        holder.item_main_grid_menu_name.setText(dataBean.getName());
        Glide.with(context).load(IMGS + dataBean.getImage()).into(holder.item_main_grid_menu_icon);
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("s","10分类");
                    bundle.putString("sort_id", String.valueOf(dataBean.getId()));
//                    ToastUtil.show(context,String.valueOf(position));
                    JumpUtil.newInstance().jumpRight(context, SearchGoodsListActivity.class,bundle);
                }
            });
    }


    /**
     * 列表点击事件
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * item点击接口
     */
    public interface MyOnItemClickListener {
        void OnItemClickListener(int position);
    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView item_main_grid_menu_icon;
        public TextView item_main_grid_menu_name;
        private View mView;

        public ViewHolder(View rootView) {
            super(rootView);
            mView = rootView;
            this.rootView = rootView;
            this.item_main_grid_menu_icon = (ImageView) rootView.findViewById(R.id.item_main_grid_menu_icon);
            this.item_main_grid_menu_name = (TextView) rootView.findViewById(R.id.item_main_grid_menu_name);
        }

    }
}
