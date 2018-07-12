package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.BusinessLoginActivity;
import com.tuoyi.threebusinesscity.activity.CoOperatorActivity;
import com.tuoyi.threebusinesscity.activity.IntegralConsumptionRecordsActivity;
import com.tuoyi.threebusinesscity.activity.MyMembersActivity;
import com.tuoyi.threebusinesscity.activity.MyPromoterActivity;
import com.tuoyi.threebusinesscity.activity.Personal_InformationActivity;
import com.tuoyi.threebusinesscity.activity.PutForwardActivity;
import com.tuoyi.threebusinesscity.activity.ShareActivity1;
import com.tuoyi.threebusinesscity.bean.MyGridMenuBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.RxActivityTool;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by md
 * on 2018/3/3 0003.
 * 个人中心菜单适配器
 */

public class MyGridMenuAdapter extends RecyclerView.Adapter<MyGridMenuAdapter.ViewHolder> {

    private Context context;
    private List<MyGridMenuBean> mdatas;

    public MyGridMenuAdapter(List<MyGridMenuBean> items) {
        mdatas = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_grid_menu, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mdatas.get(position);
        holder.itemTitle.setText(holder.mItem.getName());
        Glide.with(context).load( holder.mItem.getImg()).into(holder.itemIcon);
//        Glide.with(context).load(IMGS + holder.mItem.getImg()).into(holder.itemIcon);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(context, "可点击的：" + position + "号子布局", Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0://个人信息
                        RxActivityTool.skipActivity(context, Personal_InformationActivity.class);
                        break;
                    case 1://积分消费记录
                        RxActivityTool.skipActivity(context, IntegralConsumptionRecordsActivity.class);
                        break;
                    case 2://
                        break;
                    case 3://分享推荐
                        //RxActivityTool.skipActivity(context, ShareActivity.class);
                        RxActivityTool.skipActivity(context, ShareActivity1.class);
                        break;
                    case 4://我的伙伴
                        RxActivityTool.skipActivity(context, MyMembersActivity.class);
                        break;
                    case 5://
                        RxActivityTool.skipActivity(context, MyPromoterActivity.class);
                        break;
                    case 6://我的店铺
                        break;
                    case 7://商家登陆
                        if (TextUtils.isEmpty(UserBean.getBusineToken(context))){
                            RxActivityTool.skipActivity(context,BusinessLoginActivity.class);
                        }else {
                            RxActivityTool.skipActivity(context,CoOperatorActivity.class);
                        }

                        break;
                    case 8://
                        break;
                    case 9://
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
        @BindView(R.id.item_my_grid_menu_name)
        TextView itemTitle;
        @BindView(R.id.item_my_grid_menu_icon)
        ImageView itemIcon;
        public MyGridMenuBean mItem;
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, view);

        }
    }

}
