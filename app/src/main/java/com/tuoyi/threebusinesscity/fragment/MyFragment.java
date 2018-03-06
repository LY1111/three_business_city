package com.tuoyi.threebusinesscity.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MainGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.MyGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.NoticeMF;
import com.tuoyi.threebusinesscity.bean.MainGridMenuBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.ImageUtil;
import com.zhouwei.blurlibrary.EasyBlur;

import java.util.ArrayList;
import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * 个人中心
 */
public class MyFragment extends Fragment {
    @BindView(R.id.mainm_withdrawals_btn)
    TextView mainmWithdrawalsBtn;
    @BindView(R.id.mainm_profile_image)
    CircleImageView mainmProfileImage;
    @BindView(R.id.mainm_marqueeView)
    MarqueeView mainmMarqueeView;
    @BindView(R.id.mainm_marqueeView_tv_right)
    TextView mainmMarqueeViewTvRight;
    @BindView(R.id.mainm_grid_list)
    RecyclerView mainmGridList;
    @BindView(R.id.mainm_iv_bg)
    ImageView mainmIvbg;
    Unbinder unbinder;
    private View view;
    private LinkedList<MainGridMenuBean> headGridList;
    private MyGridMenuAdapter gridMenuadapter;
    private ArrayList<String> marqueeList;
//    底部导航list
    private String[] menuTvList = {"商城订单","消费记录","市场收入","分享推荐","我的会员","我的店中店","我是商家","代理商入口","供应商入口"};

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
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initMenuList();
        initMarqueeView();
    }

    private void initView() {
        mainmIvbg.setImageBitmap(ImageUtil.getDrawableBitmap(getContext(), R.drawable.demo_img));
    }

    /**
     * 滚动广告条设置
     */
    private void initMarqueeView() {
        marqueeList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            marqueeList.add("这是第" + i + "条滚动条。滚动吧！小傻子。。。");
        }
        //文字向上翻转
        MarqueeFactory<TextView, String> marqueeFactory = new NoticeMF(getActivity());
        //MarqueeView设置Factory
        mainmMarqueeView.setMarqueeFactory(marqueeFactory);
        //开始翻转
        mainmMarqueeView.startFlipping();
        //设置item的监听
        marqueeFactory.setOnItemClickListener(new MarqueeFactory.OnItemClickListener<TextView, String>() {
            @Override
            public void onItemClickListener(MarqueeFactory.ViewHolder<TextView, String> holder) {
            }
        });
        //设置数据
        marqueeFactory.setData(marqueeList);
    }

    @Override
    public void onStart() {
        super.onStart();
        mainmMarqueeView.startFlipping();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainmMarqueeView.stopFlipping();

    }


    /**
     * 初始化网格菜单
     */
    private void initMenuList() {
        headGridList = new LinkedList<>();
        for (int i = 0; i < menuTvList.length; i++) {
            headGridList.add(new MainGridMenuBean(R.mipmap.ic_launcher, menuTvList[i]));
        }
        mainmGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 3));
        gridMenuadapter = new MyGridMenuAdapter(headGridList);
        mainmGridList.setAdapter(gridMenuadapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @OnClick({R.id.mainm_withdrawals_btn, R.id.mainm_marqueeView_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainm_withdrawals_btn:
                break;
            case R.id.mainm_marqueeView_tv_right:
                break;
        }
    }
}
