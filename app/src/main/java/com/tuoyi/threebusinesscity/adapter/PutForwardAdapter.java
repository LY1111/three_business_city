package com.tuoyi.threebusinesscity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.BankCarkListBean;

import java.util.List;

public class PutForwardAdapter extends BaseQuickAdapter<BankCarkListBean.DataBean, BaseViewHolder> {
    public PutForwardAdapter(int layoutResId, @Nullable List<BankCarkListBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BankCarkListBean.DataBean item) {


        if (item.isChecked()) {
            Glide.with(mContext).load(R.mipmap.checked).into((ImageView) helper.getView(R.id.img_checked));
        } else {
            Glide.with(mContext).load(R.mipmap.unchecked).into((ImageView) helper.getView(R.id.img_checked));
        }
        helper.setText(R.id.tv_cardNum, "（" + item.getBankCardNo().substring(15) + "）");
        helper.setText(R.id.tv_cardName, item.getBankName());
    }
}
