package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.GeneralDetailsBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * Created by md
 * 商家商品列表适配器
 * on 2018/3/3 0003.
 */

public class GeneralDetailsAdapter extends RecyclerView.Adapter<GeneralDetailsAdapter.ViewHolder> {


    private Context context;
    private List<GeneralDetailsBean.DataBean.GoodsBean> mdatas;

    public GeneralDetailsAdapter(List<GeneralDetailsBean.DataBean.GoodsBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_general_details, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.mTitle.setText(holder.mItem.getName());
        holder.mMoney.setText("￥"+holder.mItem.getPrice());
        Glide.with(context).load(IMGS + holder.mItem.getImage()).into(holder.mImg);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
//                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);
            }
        });
        holder.mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击付款："+holder.mItem.getGoods_id(), Toast.LENGTH_SHORT).show();
                holder.mItem.getGoods_id();
//                UserBean.setPosID(context,String.valueOf(holder.mItem.getUid()));
//                RxActivityTool.skipActivity(context, GeneralDetailsActivity.class);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_generalDetails_img)
        ImageView mImg;
        @BindView(R.id.item_generalDetails_title)
        TextView mTitle;
        @BindView(R.id.item_generalDetails_money)
        TextView mMoney;
        @BindView(R.id.item_generalDetails_btn)
        TextView mBtn;
        public GeneralDetailsBean.DataBean.GoodsBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
