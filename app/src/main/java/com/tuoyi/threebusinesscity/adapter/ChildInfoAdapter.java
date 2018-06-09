package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.OrderDetailsActivity;
import com.tuoyi.threebusinesscity.bean.GetWholeOrderBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;


/**
 * 内部的RecyclerView
 * 内容为：
 * imageView + textView
 * Created by gaoshiwei on 2017/9/19.
 */

public class ChildInfoAdapter extends RecyclerView.Adapter<ChildInfoAdapter.ViewHolder> {


    private Context context;
    private int id;
    private List<GetWholeOrderBean.DataBean.OrderGoodsBean> list; // List 集合（里面是image+text）

    /**
     * 构造函数
     *
     * @param context
     * @param list
     */
    public ChildInfoAdapter(Context context, List<GetWholeOrderBean.DataBean.OrderGoodsBean> list,int id) {
        this.context = context;
        this.list = list;
        this.id=id;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_orderadapter_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GetWholeOrderBean.DataBean.OrderGoodsBean info = list.get(position);
        Glide.with(context).load(IMGS + info.getImage()).into(holder.itemOrderListImg);  //商品图片
        holder.itemOrderListNum.setText(info.getQuantity()+"");
        holder.itemOrderListTitle.setText(info.getName());
        holder.itemOrderListPrice.setText(info.getPrice()+"");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id",id);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * static ViewHolder
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_orderList_img)
        ImageView itemOrderListImg;
        @BindView(R.id.item_orderList_title)
        TextView itemOrderListTitle;
        @BindView(R.id.item_orderList_price)
        TextView itemOrderListPrice;
        @BindView(R.id.item_orderList_num)
        TextView itemOrderListNum;
        public View mView;
        public ViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            ButterKnife.bind(this, itemView);
        }
    }


}
