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
import com.tuoyi.threebusinesscity.bean.MainGridMenuBean;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by md
 * on 2018/3/3 0003.
 */

public class MainGridMenuAdapter extends RecyclerView.Adapter<MainGridMenuAdapter.ViewHolder> {

    private Context context;
    private LinkedList<MainGridMenuBean> mdatas;

    public MainGridMenuAdapter(LinkedList<MainGridMenuBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_grid_menu, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.itemTitle.setText(holder.mItem.getTitle());
        Glide.with(context).load(holder.mItem.getmImg()).into(holder.itemIcon);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "可点击的：" + position + "号子布局", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0://
                        break;
                    case 1://
                        break;
                    case 2://
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mdatas.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_main_grid_menu_name)
        TextView itemTitle;
        @BindView(R.id.item_main_grid_menu_icon)
        ImageView itemIcon;
        public MainGridMenuBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
