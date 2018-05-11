package com.tuoyi.threebusinesscity.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.adapter.MainBottomShopAdapter;
import com.tuoyi.threebusinesscity.adapter.MainGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.NoticeMF;
import com.tuoyi.threebusinesscity.bean.MainBottomShopBean;
import com.tuoyi.threebusinesscity.bean.MainGridMenuBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 * 三商商城（线上）
 */
public class ShopFragment extends Fragment {

    @BindView(R.id.mains_banner)
    Banner mainsBanner;
    @BindView(R.id.mains_head_grid_list)
    RecyclerView mainsHeadGridList;
    @BindView(R.id.mains_marqueeView)
    MarqueeView mainsMarqueeView;
    @BindView(R.id.mains_marqueeView_tv_right)
    TextView mainsMarqueeViewTvRight;
    @BindView(R.id.mains_location)
    LinearLayout mainsLocation;
    @BindView(R.id.mains_search_et)
    EditText mainsSearchEt;
    Unbinder unbinder;
    private View view;
    private int[] image = {R.drawable.demo_img, R.drawable.demo_img, R.drawable.demo_img};
    private List<Integer> images = new ArrayList<>();
    private List<String> marqueeList = new ArrayList<>();
    private LinkedList<MainGridMenuBean> headGridList;
    private MainGridMenuAdapter gridMenuadapter;

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
        view = inflater.inflate(R.layout.fragment_shop, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initMenuList();
        initMarqueeView();
    }

    /**
     * 初始化顶部网格菜单
     */
    private void initMenuList() {
        headGridList = new LinkedList<>();
//        for (int i = 0; i < 8; i++) {
//            headGridList.add(new MainGridMenuBean(R.mipmap.ic_launcher, "第" + i + "个分类"));
//        }
//        mainsHeadGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 4));
//        gridMenuadapter = new MainGridMenuAdapter(headGridList);
//        mainsHeadGridList.setAdapter(gridMenuadapter);
    }

    /**
     * 轮播图设置
     */
    private void initBanner() {
        images = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            images.add(image[i]);
        }
        //设置图片加载器
        mainsBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mainsBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mainsBanner.start();
        mainsBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
            }
        });
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
        mainsMarqueeView.setMarqueeFactory(marqueeFactory);
        //开始翻转
        mainsMarqueeView.startFlipping();
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
        mainsMarqueeView.startFlipping();
        //开始轮播
        mainsBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainsMarqueeView.stopFlipping();
        //结束轮播
        mainsBanner.stopAutoPlay();
    }

    @OnClick({R.id.mains_marqueeView_tv_right, R.id.mains_location})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mains_marqueeView_tv_right:
                break;
            case R.id.mains_location:
                break;
        }
    }
}
