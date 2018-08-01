package com.tuoyi.threebusinesscity.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.KillBean;
import com.tuoyi.threebusinesscity.util.RushBuyCountDownTimerView;

import java.util.List;

import static com.tuoyi.threebusinesscity.url.Config.IMGS;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/2 9:23
 * @描述 ${TODO}
 */

public class KillAdapter1 extends BaseQuickAdapter<KillBean.DataBean, BaseViewHolder> {
    public KillAdapter1(int layoutResId, @Nullable List<KillBean.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, KillBean.DataBean item) {
        ImageView imageView11111=helper.getView(R.id.mHandPick1_pic);
        RequestOptions options=new RequestOptions().placeholder(R.drawable.s_img);
        Glide.with(mContext).load(IMGS + item.getImage()).apply(options).into(imageView11111);

        helper.setText(R.id.mHandPick1_text, item.getName());
        helper.setText(R.id.mHandPick1_point, "积分" + item.getPrice());
         /* 设置倒计时 单位秒 */
        int sum = 6000;
        RushBuyCountDownTimerView timeView1 = helper.getView(R.id.timeView1);
        timeView1.addTime(sum);
        timeView1.start();
    }
}
