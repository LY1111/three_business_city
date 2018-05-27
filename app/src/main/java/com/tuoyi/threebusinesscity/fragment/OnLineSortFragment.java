package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuoyi.threebusinesscity.R;

/**
 * Created by Administrator on 2018/5/27 0027.
 */

public class OnLineSortFragment extends android.support.v4.app.Fragment {
    private View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.activity_goods_sort,null);
        }
        return view;
    }
}
