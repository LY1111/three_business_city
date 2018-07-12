package com.tuoyi.threebusinesscity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BankCarkListBean;
import com.tuoyi.threebusinesscity.bean.PayNowBean;

import java.util.List;

public class BankCardListAdapter extends BaseQuickAdapter<BankCarkListBean.DataBean,BaseViewHolder> {
    public BankCardListAdapter(int layoutResId, @Nullable List<BankCarkListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCarkListBean.DataBean item) {
        helper.setText(R.id.tv_bankcard,"***************"+item.getBankCardNo().substring(15));
        helper.setText(R.id.tv_cardName,item.getBankName());
    }
}
