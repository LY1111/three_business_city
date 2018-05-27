package com.tuoyi.threebusinesscity.fragment;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.XRefreshViewFooter;
import com.andview.refreshview.XRefreshViewHeader;
import com.gongwen.marqueen.MarqueeFactory;
import com.gongwen.marqueen.MarqueeView;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.orhanobut.logger.Logger;
import com.tuoyi.threebusinesscity.R;
import com.tuoyi.threebusinesscity.activity.MainShopDetailsActivity;
import com.tuoyi.threebusinesscity.adapter.MainBottomShopAdapter;
import com.tuoyi.threebusinesscity.adapter.MainGridMenuAdapter;
import com.tuoyi.threebusinesscity.adapter.NoticeMF;
import com.tuoyi.threebusinesscity.bean.BannerBean;
import com.tuoyi.threebusinesscity.bean.MainBottomShopBean;
import com.tuoyi.threebusinesscity.bean.MainGridMenuBean;
import com.tuoyi.threebusinesscity.bean.UserBean;
import com.tuoyi.threebusinesscity.util.FullyGridLayoutManager;
import com.tuoyi.threebusinesscity.util.JumpUtil;
import com.tuoyi.threebusinesscity.util.KeyBoardUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.tuoyi.threebusinesscity.url.Config.S;

/**
 * A simple {@link Fragment} subclass.
 * 首页
 */
public class MainFragment extends Fragment implements AMapLocationListener {
    @BindView(R.id.mainf_location)
    LinearLayout mainfLocation;
    @BindView(R.id.mainf_location_tv)
    TextView mLocationTv;
    @BindView(R.id.mainf_search_et)
    EditText mainfSearchEt;
    @BindView(R.id.mainf_banner)
    Banner mainfBanner;
    @BindView(R.id.mainf_XRefreshView)
    XRefreshView mRefreshView;
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
    private List<String> images = new ArrayList<>();
    private List<String> marqueeList = new ArrayList<>();
    private List<MainGridMenuBean.DataBean> headGridList;
    private List<MainBottomShopBean.DataBean> bottomShopList = new ArrayList<>();
    private MainGridMenuAdapter gridMenuadapter;
    private MainBottomShopAdapter bottomShopAdapter;

    private boolean selectType;


    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int pagenow = 1;
    private double mLng = 0.00;
    private double mLat = 0.00;
    private static MainFragment fragment;
    private int mPosition = 0;
    private int mListType = 0;
    private String name = "";

    public MainFragment() {
    }

    public static MainFragment newInstance() {
        fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, view);
        initLocation();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMarqueeView();
        okGoBanner();
        okGoMenuList();
        initRefreshView();
        mainfSearchEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Toast.makeText(getContext(), "你点击了搜索键。。。。", Toast.LENGTH_SHORT).show();
                    name = mainfSearchEt.getText().toString().trim();
                    if (!TextUtils.isEmpty(name)) {
                        mListType = 2;
                        setPagenow(1);
                        initOkGoListTwo(name);
                    } else {
                        Toast.makeText(getContext(), "请输入后搜索商家", Toast.LENGTH_SHORT).show();
                    }
                    KeyBoardUtils.closeKeybord(mainfSearchEt, getContext());
                    return true;
                }
                return false;
            }
        });
    }

    private void okGoBanner() {
        OkGo.<String>post(S + "api/app/link")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        BannerBean bannerBean = gson.fromJson(response.body(), BannerBean.class);
                        if (bannerBean.getCode() == 200) {
                            List<BannerBean.DataBean> data = bannerBean.getData();
                            images = new ArrayList<>();
                            if (data!=null) {
                                for (int i = 0; i < bannerBean.getData().size(); i++) {
                                    images.add(data.get(i).getPicurl());
                                }
                            }
                            initBanner();
                        }
//                        Toast.makeText(getContext(), bannerBean.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
    }


    private void initOkGoListTwo(String name) {
        OkGo.<String>post(S + "api/app/search_business")
                .tag(this)
                .params("name", name)
                .params("page", pagenow)
                .params("num", 10)
                .params("lng", mLng)
                .params("lat", mLat)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
//                        bottomShopList = new LinkedList<>();
                        MainBottomShopBean shopBean = gson.fromJson(response.body(), MainBottomShopBean.class);
                        if (shopBean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (pagenow == 1) {
                                bottomShopList = new ArrayList<>();
                                bottomShopList = shopBean.getData();
                            } else {
                                bottomShopList.addAll(shopBean.getData());
                            }
                            mRefreshView.stopRefresh();
                        } else {
//                            Toast.makeText(getContext(), shopBean.getMessage(), Toast.LENGTH_SHORT).show();
                            if (pagenow == 1) {
                                bottomShopList = new LinkedList<>();
                            }
                            mRefreshView.setLoadComplete(true);
                        }

                        initShopList();
                    }
                });
    }

    private void okGoMenuList() {
        OkGo.<String>post(S + "api/app/get_business_classification")
                .tag(this)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Gson gson = new Gson();
                        MainGridMenuBean gridMenuBean = gson.fromJson(response.body(), MainGridMenuBean.class);
                        if (gridMenuBean.getCode() == 200) {
                            headGridList = new LinkedList<>();
                            headGridList.addAll(gridMenuBean.getData());
                            initMenuList();
                        } else {
                            Toast.makeText(getContext(), gridMenuBean.getMessage(), Toast.LENGTH_SHORT).show();
                        }


                    }
                });
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity().getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        AMapLocationClientOption option = new AMapLocationClientOption();
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
        if (null != mLocationClient) {
            mLocationClient.setLocationOption(option);
            //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
            mLocationClient.stopLocation();
            mLocationClient.startLocation();
        }
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);

        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，
        // 启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);

        //设置是否允许模拟位置,默认为true，允许模拟位置
        mLocationOption.setMockEnable(true);

        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);

        //关闭缓存机制
        mLocationOption.setLocationCacheEnable(false);

        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
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
                        if (mainfLlScreen.getVisibility() == View.VISIBLE) {
                            mainfLlScreen.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mainf_menu_rb_b:
                        if (mainfLlScreen.getVisibility() == View.VISIBLE) {
                            mainfLlScreen.setVisibility(View.GONE);
                        }
                        break;
                    case R.id.mainf_menu_rb_c:
                        if (mainfLlScreen.getVisibility() == View.GONE) {
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
        mainfHeadGridList.setLayoutManager(new FullyGridLayoutManager(getContext(), 5));
        gridMenuadapter = new MainGridMenuAdapter(headGridList);
        mainfHeadGridList.setAdapter(gridMenuadapter);
        gridMenuadapter.setOnItemClickListener(new MainGridMenuAdapter.MyOnItemClickListener() {
            @Override
            public void OnItemClickListener(int position) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("id",headGridList.get(position).getId());
//                bundle.putDouble("lat",mLat);
//                bundle.putDouble("lon",mLng);
//                JumpUtil.newInstance().jumpRight(getContext(), MainShopDetailsActivity.class,bundle);
                setPagenow(1);
                mListType = 1;
                mPosition = position;
                initOkGoList(mPosition);
            }
        });
    }

    /**
     * 初始化底部商家列表
     */
    private void initShopList() {
        mainfBottomShopList.setLayoutManager(new LinearLayoutManager(getContext()));
        bottomShopAdapter = new MainBottomShopAdapter(bottomShopList);
        mainfBottomShopList.setAdapter(bottomShopAdapter);
    }

    /**
     * 轮播图设置
     */
    private void initBanner() {
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
        if (mLocationClient.isStarted()) {
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mLocationClient.isStarted()) {
            mLocationClient.startLocation();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick({R.id.mainf_location, R.id.marqueeView_tv_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mainf_location://定位按钮
                Toast.makeText(getContext(), "正在定位...", Toast.LENGTH_SHORT).show();
                mainfLocation.setEnabled(false);
                if (mLocationClient.isStarted()) {
                    mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                    mLocationClient.startLocation();
                } else {
                    mLocationClient.startLocation();
                }
                break;
            case R.id.marqueeView_tv_right://更多列表，可能要跳转Fragment
                break;
        }
    }


    //定位回调监听器
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析aMapLocation获取相应内容。
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
                aMapLocation.getLatitude();//获取纬度
                aMapLocation.getLongitude();//获取经度
                aMapLocation.getAccuracy();//获取精度信息
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                aMapLocation.getAoiName();//获取当前定位点的AOI信息
                aMapLocation.getBuildingId();//获取当前室内定位的建筑物Id
                aMapLocation.getFloor();//获取当前室内定位的楼层
                aMapLocation.getGpsAccuracyStatus();//获取GPS的当前状态
                //获取定位时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);
                mLng = aMapLocation.getLongitude();//获取经度
                mLat = aMapLocation.getLatitude();//获取纬度
                if (TextUtils.isEmpty(aMapLocation.getCity().trim())) {
                    if (mLocationClient.isStarted()) {
                        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁

                    }
                    mLocationClient.startLocation();
                } else {
                    mLocationTv.setText(aMapLocation.getCity().trim());
                    if (mLocationClient.isStarted()) {
                        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
                        mainfLocation.setEnabled(true);
                    }
                    UserBean.setLat(getContext(), String.valueOf(mLat));
                    UserBean.setLng(getContext(), String.valueOf(mLng));
                    if (headGridList.size() != 0) {
                        initOkGoList(mPosition);
                    }
//                    Toast.makeText(getContext(), "当前位置：" + aMapLocation.getCity().trim(), Toast.LENGTH_SHORT).show();
                }

            } else {
                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                mainfLocation.setEnabled(true);
//                Toast.makeText(getContext(), "ErrCode:" + aMapLocation.getErrorCode() + "\n" + "errInfo:" + aMapLocation.getErrorInfo(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void initOkGoList(int pos) {
        OkGo.<String>post(S + "api/app/get_business_all")
                .tag(this)
                .params("id", headGridList.get(pos).getId())
                .params("page", pagenow)
                .params("num", 10)
                .params("lng", mLng)
                .params("lat", mLat)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(Response<String> response) {
                        Logger.json(response.body());
                        Gson gson = new Gson();
//                        bottomShopList = new LinkedList<>();
                        MainBottomShopBean shopBean = gson.fromJson(response.body(), MainBottomShopBean.class);
                        if (shopBean.getCode() == 200) {
                            mRefreshView.setLoadComplete(false);
                            if (pagenow == 1) {
                                bottomShopList = new ArrayList<>();
                                bottomShopList = shopBean.getData();
                            } else {
                                bottomShopList.addAll(shopBean.getData());
                            }
                            mRefreshView.stopRefresh();
                        } else {
//                            Toast.makeText(getContext(), shopBean.getMessage(), Toast.LENGTH_SHORT).show();
                            if (pagenow == 1) {
                                bottomShopList = new LinkedList<>();
                            }
                            mRefreshView.setLoadComplete(true);
                        }

                        initShopList();
                    }
                });
    }

    private void setPagenow(int page) {
        this.pagenow = page;
    }

    private void initRefreshView() {
        mRefreshView.setPinnedTime(500);//设置刷新后头布局停留的时间
        mRefreshView.setMoveForHorizontal(true);//设置不获取横向滑动的焦点手势
        mRefreshView.setPullLoadEnable(true);//是否支持上拉加载
        mRefreshView.setPullRefreshEnable(true);//是否支持下拉刷新
        mRefreshView.setAutoLoadMore(false);//滑动到底部是否自动加载更多
        mRefreshView.setPinnedContent(true);//刷新时，不让里面的列表上下滑动
        mRefreshView.setCustomHeaderView(new XRefreshViewHeader(getContext(), null));//设置头布局
        mRefreshView.setCustomFooterView(new XRefreshViewFooter(getContext(), null));//设置脚布局虽然没啥用
        mRefreshView.enableReleaseToLoadMore(true);
        mRefreshView.enableRecyclerViewPullUp(true);
        mRefreshView.setSilenceLoadMore(false);//是否静默加载
        mRefreshView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                pagenow = 1;
                switch (mListType) {
                    case 1:
                        initOkGoList(mPosition);
                        break;
                    case 2:
                        initOkGoListTwo(name);
                        break;
                    default:
                        initOkGoList(mPosition);
                        break;
                }
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pagenow++;
                switch (mListType) {
                    case 1:
                        initOkGoList(mPosition);
                        break;
                    case 2:
                        initOkGoListTwo(name);
                        break;
                    default:
                        initOkGoList(mPosition);
                        break;
                }
            }
        });
    }
}
