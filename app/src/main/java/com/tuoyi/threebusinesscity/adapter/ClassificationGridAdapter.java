package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.utils.LogUtils;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.ShopClassificationInformationBean;

import java.util.List;

/**
 * @创建者 Liyan
 * @创建时间 2018/6/1 16:18
 * @描述 ${TODO}
 */

public class ClassificationGridAdapter extends BaseAdapter {
    private List<ShopClassificationInformationBean.DataBean.LowerBean> datas;
    private LayoutInflater                                             inflater;

    public static class ViewHolder {
        private TextView tvName;
        private TextView     mView;
    }

    public ClassificationGridAdapter(Context context, List<ShopClassificationInformationBean.DataBean.LowerBean> datas) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public ShopClassificationInformationBean.DataBean.LowerBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassificationGridAdapter.ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_classiftion2, parent, false);
            holder = new ClassificationGridAdapter.ViewHolder();
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.mView = view.findViewById(R.id.view1);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LogUtils.e("position========"+position+"===="+datas.size());
        if ((position+1) % 3 == 0) {
            holder.mView.setVisibility(View.GONE);
        }
        holder.tvName.setText(datas.get(position).getName());

        return view;
    }

}
