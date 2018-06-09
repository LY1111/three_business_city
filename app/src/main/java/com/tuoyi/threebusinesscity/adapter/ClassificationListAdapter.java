package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.ShopClassificationInformationBean;

import java.util.List;

/**
 * @创建者 Liyan
 * @创建时间 2018/3/19 11:24
 * @描述 ${TODO}
 */

public class ClassificationListAdapter extends BaseAdapter {
    private List<ShopClassificationInformationBean.DataBean> datas;
    private LayoutInflater                          inflater;
    private int                                     selectedPosition;
    private Context context;

    public static class ViewHolder {
        private TextView tvName;
        private LinearLayout itemlayoutb;
    }

    public void selectedPosition(int selectedPosition) {
        this.selectedPosition=selectedPosition;
    }

    public ClassificationListAdapter(Context context, List<ShopClassificationInformationBean.DataBean> datas) {
        this.context=context;
        inflater = LayoutInflater.from(context);
        this.datas = datas;

    }

    @Override
    public int getCount() {

        return  datas.size();
    }

    @Override
    public ShopClassificationInformationBean.DataBean getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = inflater.inflate(R.layout.item_classiftion, parent, false);
            holder = new ViewHolder();
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.itemlayoutb = view.findViewById(R.id.itemlayoutb);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (selectedPosition == position) {
            holder.itemlayoutb.setBackgroundColor(Color.parseColor("#ffffff"));
        } else {
            holder.itemlayoutb.setBackgroundColor(Color.parseColor("#DCDBDB"));
        }

        holder.tvName.setText(datas.get(position).getName());

        return view;
    }
}
