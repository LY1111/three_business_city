package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.bean.ShopTypeBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.icon;
import static android.R.id.list;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ShopTypeAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ShopTypeBean.DataBean> beanList = new ArrayList<>();
    private LinkedHashMap <Integer,Boolean> isCheck = new LinkedHashMap <Integer,Boolean>();


    public ShopTypeAdapter(Context context, List<ShopTypeBean.DataBean> beanList) {
        this.context = context;
        this.beanList = beanList;
        inflater = LayoutInflater.from(context);
        initCheck(false);
    }


    private void initCheck(boolean b) {
        for (int i = 0; i < beanList.size() ; i++) {
            isCheck.put(i,b);
        }
    }



    @Override
    public int getCount() {
        return beanList != null ? beanList.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return beanList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return beanList.size() != 0 ? position : 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shop_type, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ShopTypeBean.DataBean bean = beanList.get(position);
        holder.mItem.setText(bean.getName());
        final ViewHolder finalHolder = holder;
        holder.mItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCheck.put(position,b);
            }
        });
        if (isCheck.get(position) == null){
            isCheck.put(position,false);
        }
        holder.mItem.setChecked(isCheck.get(position));
        return convertView;
    }

    public LinkedHashMap<Integer, Boolean> getMap(){
        return isCheck;
    }

    public static class ViewHolder {
        public View rootView;
        public CheckBox mItem;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mItem = (CheckBox) rootView.findViewById(R.id.mItem);
        }

    }
}
