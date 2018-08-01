package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.MyMembersBean;
import com.tuoyi.threebusinesscity.url.Config;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 消费积分详情
 * Created by md
 * on 2018/3/7 0007.
 */

public class MyMembersAdapter extends RecyclerView.Adapter<MyMembersAdapter.ViewHolder> {

    private Context context;
    private List<MyMembersBean.DataBean.UpperMemberBean> mdatas;

    public MyMembersAdapter(List<MyMembersBean.DataBean.UpperMemberBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_members, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mName.setText(mdatas.get(position).getUsername());
        RequestOptions options=new RequestOptions().placeholder(R.drawable.demo_img);
        Glide.with(context).load(Config.IMGS+(mdatas.get(position).getUserpic())).apply(options).into(holder.mImage);
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
        @BindView(R.id.item_my_menbers_image)
        CircleImageView mImage;
        @BindView(R.id.item_my_menbers_name)
        TextView mName;
        @BindView(R.id.item_my_menbers_location)
        TextView mLocation;
        @BindView(R.id.item_my_menbers_time)
        TextView mTime;
        @BindView(R.id.item_my_menbers_money)
        TextView mMoney;
        public MyMembersBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
