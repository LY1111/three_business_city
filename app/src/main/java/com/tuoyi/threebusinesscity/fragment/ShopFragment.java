package com.tuoyi.threebusinesscity.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuoyi.threebusinesscity.R;

/**
 * A simple {@link Fragment} subclass.
 * 三商商城
 */
public class ShopFragment extends Fragment {

    private View view;

    public ShopFragment() {
        // Required empty public constructor
    }

    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_shop, container, false);
        return view;
    }

}
