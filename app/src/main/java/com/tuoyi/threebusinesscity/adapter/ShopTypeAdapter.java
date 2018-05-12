package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/5/11 0011.
 */

public class ShopTypeAdapter extends BaseAdapter {
    public static HashMap<Integer,Boolean> isSelected;
    private Context context;
    private LayoutInflater inflater;
    private List<HashMap<String,Object>> list = null;
    private String keyString [] = null;
    private String itemString = null;
    private int idValue[] = null;



    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
