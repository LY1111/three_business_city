package com.tuoyi.threebusinesscity.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuoyi.threebusinesscity.R;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 */
public class MyFragment extends Fragment {
    private View view;

    public MyFragment() {
        // Required empty public constructor
    }
    public static MyFragment newInstance() {
        MyFragment fragment = new MyFragment();
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }
}
