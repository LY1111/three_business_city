package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
 * 首页
 */
public class MainFragment extends Fragment {
    @BindView(R.id.mainf_location)
    LinearLayout mainfLocation;
    @BindView(R.id.mainf_search_et)
    EditText mainfSearchEt;
    @BindView(R.id.mainf_banner)
    Banner mainfBanner;
    @BindView(R.id.mainf_head_grid_list)
    RecyclerView mainfHeadGridList;
    @BindView(R.id.mainf_marqueeView)
    MarqueeView mainfMarqueeView;
    @BindView(R.id.marqueeView_tv_right)
    TextView marqueeViewTvRight;
    @BindView(R.id.mainf_menu_rb_a)
    RadioButton mainfMenuRbA;
    @BindView(R.id.mainf_menu_rb_b)
    RadioButton mainfMenuRbB;
    @BindView(R.id.mainf_menu_rb_c)
    RadioButton mainfMenuRbC;
    @BindView(R.id.mainf_rg_menu)
    RadioGroup mainfRgMenu;
    @BindView(R.id.mainf_screen_rb_a)
    RadioButton mainfScreenRbA;
    @BindView(R.id.mainf_screen_rb_b)
    RadioButton mainfScreenRbB;
    @BindView(R.id.mainf_screen_rb_c)
    RadioButton mainfScreenRbC;
    @BindView(R.id.mainf_rg_screen)
    RadioGroup mainfRgScreen;
    @BindView(R.id.mainf_bottom_shop_list)
    RecyclerView mainfBottomShopList;
    Unbinder unbinder;
    @BindView(R.id.mainf_ll_screen)
    LinearLayout mainfLlScreen;
    private View view;
    private int[] image = {R.drawable.demo_img, R.drawable.demo_img, R.drawable.demo_img};
    private List<Integer> images = new ArrayList<>();
    private List<String> marqueeList = new ArrayList<>();
    private LinkedList<MainGridMenuBean> headGridList;
    private List<MainBottomShopBean> bottomShopList;
    private MainGridMenuAdapter gridMenuadapter;
    private MainBottomShopAdapter bottomShopAdapter;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initBanner();
        initMarqueeView();
        initMenuList();
        initListener();
        initShopList();
    }

    /**
     * 所有的监听事件
     */
    private void initListener() {
        mainfRgMenu.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.mainf_menu_rb_a:
                        if (mainfLlScreen.getVisibility() == View.VISIBLE){
                            mainfLlScreen.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mainf_menu_rb_b:
                        if (mainfLlScreen.getVisibility() == View.VISIBLE){
                            mainfLlScreen.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mainf_menu_rb_c:
                        if (mainfLlScreen.getVisibility() == View.GONE){
                            mainfLlScreen.setVisibility(View.VISIBLE);
                        }
                        break;
                }
            }
        });
        mainfRgScreen.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
//                switch (checkedId) {
//
//                }
            }
        });
        mainfRgMenu.check(R.id.mainf_menu_rb_a);
        mainfRgScreen.check(R.id.mainf_screen_rb_a);
    }

    /**
     * 初始化顶部网格菜单
     */
    private void initMenuList() {
        headGridList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            headGridList.add(new MainGridMenuBean(R.mipmap.ic_launcher, "第" + i + "个分类"));
        }
        mainfHeadGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 5));
        gridMenuadapter = new MainGridMenuAdapter(headGridList);
        mainfHeadGridList.setAdapter(gridMenuadapter);
    } /**
     * 初始化底部商家列表
     */
    private void initShopList() {
        bottomShopList = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            bottomShopList.add(new MainBottomShopBean(R.mipmap.ic_launcher, "第" + i + "个商家"));
        }
        mainfBottomShopList.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomShopAdapter = new MainBottomShopAdapter(bottomShopList);
        mainfBottomShopList.setAdapter(bottomShopAdapter);
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
        mainfBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        mainfBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mainfBanner.start();
        mainfBanner.setOnBannerListener(new OnBannerListener() {
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
        mainfMarqueeView.setMarqueeFactory(marqueeFactory);
        //开始翻转
        mainfMarqueeView.startFlipping();
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
        mainfMarqueeView.startFlipping();
        //开始轮播
        mainfBanner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainfMarqueeView.stopFlipping();
        //结束轮播
        mainfBanner.stopAutoPlay();

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.mainf_location, R.id.marqueeView_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainf_location://定位按钮
                break;
            case R.id.marqueeView_tv_right://更多列表，可能要跳转Fragment
                break;
        }
    }
}
