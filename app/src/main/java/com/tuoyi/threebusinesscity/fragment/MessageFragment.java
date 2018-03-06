package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.iwgang.countdownview.CountdownView;

/**
 * A simple {@link Fragment} subclass.
 * 消息通知
 */
public class MessageFragment extends Fragment {

    @BindView(R.id.mainme_img)
    ImageView mainmeImg;
    Unbinder unbinder;
    @BindView(R.id.mainme_cdv)
    CountdownView mainmeCdv;
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
        view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        mainmeImg.setImageBitmap(ImageUtil.getDrawableBitmap(getContext(), R.drawable.demo_img));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        mainmeCdv.start(995550000);
    }

    @Override
    public void onStop() {
        super.onStop();
        mainmeCdv.stop();
    }
}
