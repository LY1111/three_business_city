package com.tuoyi.threebusinesscity.adapter;

import android.support.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.MyPromoterBean;
import com.tuoyi.threebusinesscity.url.Config;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 消费积分详情
 * Created by md
 * on 2018/3/7 0007.
 */

public class MyPromoterAdapter extends BaseQuickAdapter<MyPromoterBean.DataBean.BusinessListBean,BaseViewHolder> {


    public MyPromoterAdapter(int layoutResId, @Nullable List<MyPromoterBean.DataBean.BusinessListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPromoterBean.DataBean.BusinessListBean item) {
        CircleImageView imageView=helper.convertView.findViewById(R.id.item_my_promoter_image);
        Glide.with(mContext).load(Config.IMGS+item.getImage()).into(imageView);
        helper.setText(R.id.item_my_promoter_name,item.getShop_name());

    }
}
