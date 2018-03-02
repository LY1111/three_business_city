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
 * 消息通知
 */
public class MessageFragment extends Fragment {

    private View view;
    public MessageFragment() {
        // Required empty public constructor
    }

    public static MessageFragment newInstance() {
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_message, container, false);
        return view;
    }
}
