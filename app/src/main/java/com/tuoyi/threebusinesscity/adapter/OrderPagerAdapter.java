package com.tuoyi.threebusinesscity.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tuoyi.threebusinesscity.R;

import java.util.List;

public class OrderPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;
    private Context mContext;

    public OrderPagerAdapter(Context mContext,FragmentManager fm, List<Fragment> list_fragment) {
        super(fm);
        this.mContext=mContext;
        this.mFragmentList = list_fragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

}
