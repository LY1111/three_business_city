package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.tuoyi.threebusinesscity.R;

/**
 * Created by md
 * on 2017/11/21 0021.
 * 滚动广告条专用适配
 */

public class NoticeMF extends MarqueeFactory<TextView, String> {
    private LayoutInflater inflater;

    public NoticeMF(Context mContext) {
        super(mContext);
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public TextView generateMarqueeItemView(String data) {
        TextView mView = (TextView) inflater.inflate(R.layout.item_notice, null);
        mView.setText(data);
        return mView;
    }
}