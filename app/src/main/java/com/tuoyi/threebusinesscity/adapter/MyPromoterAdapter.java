package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.MyPromoterBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 消费积分详情
 * Created by md
 * on 2018/3/7 0007.
 */

public class MyPromoterAdapter extends RecyclerView.Adapter<MyPromoterAdapter.ViewHolder> {

    private Context context;
    private List<MyPromoterBean> mdatas;

    public MyPromoterAdapter(List<MyPromoterBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_promoter, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.mName.setText(holder.mItem.getPromoterName());
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
        @BindView(R.id.item_my_promoter_image)
        CircleImageView mImage;
        @BindView(R.id.item_my_promoter_name)
        TextView mName;
        @BindView(R.id.item_my_promoter_location)
        TextView mLocation;
        @BindView(R.id.item_my_promoter_time)
        TextView mTime;
        public MyPromoterBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
