package com.tuoyi.threebusinesscity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.PayNowBean;

import java.util.List;

public class SmallBPayAdapter extends BaseQuickAdapter<PayNowBean,BaseViewHolder> {
    public SmallBPayAdapter(int layoutResId, @Nullable List<PayNowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PayNowBean item) {
        helper.setText(R.id.tv_pay_name,item.getName());
        if (item.getNum()==1){
            helper.setImageResource(R.id.img_pay,R.mipmap.wechat_pay);
        }else {
            helper.setImageResource(R.id.img_pay,R.mipmap.ali_pay);
        }
        if (item.isChecked()){
            Glide.with(mContext).load(R.mipmap.checked).into((ImageView) helper.getView(R.id.img_checked));
        }else {
            Glide.with(mContext).load(R.mipmap.unchecked).into((ImageView) helper.getView(R.id.img_checked));
        }

    }

    @Override
    public void setNewData(@Nullable List<PayNowBean> data) {
        super.setNewData(data);
    }
}
